package controller;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class EbayUserController {

    private static Map<String,List<String>> map =new HashMap<>();

    /**
     *
     * @param userInfo ewoidXNlcklkIjoxMjM0NTYsCiJhY2NvdW50TmFtZSI6ICJYWFhYWFhYIiwKInJvbGUiOiAiYWRtaW4iCn0=
     * @param addUserBo
     * @return
     */
    @PostMapping("/admin/addUser")
    public ResultBean<String> addUser(@RequestHeader(value = "userInfo") String userInfo,@RequestBody AddUserBo addUserBo){
        ResultBean resultBean = new ResultBean();

        if (StringUtils.isEmpty(addUserBo.getUserId())||addUserBo.getEndpoint().isEmpty()){
            return ResultUtils.error(CodeMsgEnum.PARAMETER_ERROR);
        }
        //请求头信息可以处理的更细节
        if (StringUtils.isEmpty(userInfo)){
            return ResultUtils.error(CodeMsgEnum.HEADER_NULL);
        }
        UserBo user  = checkBase64(userInfo);

        if (user==null||!"admin".equals(user.getRole())){
            return ResultUtils.error(CodeMsgEnum.NOT_ADMIN);
        }
        //添加权限
        List<String>  endpoint  =  addUserBo.getEndpoint();

        UserBo userBo =new UserBo();
        userBo.setUserId(addUserBo.getUserId());
        userBo.setEndpoint(endpoint);

        //本地放权限
        map.put(addUserBo.getUserId(), endpoint);

//          String userFile = JSONObject.toJSONString(user);
//         saveUser(userFile,"访问名单");
        return ResultUtils.success();

    }

    @GetMapping("/user/{resource}")
    public ResultBean<String> checkUser(@PathVariable("resource") String resource,@RequestHeader(value = "userInfo") String userInfo){
        if (StringUtils.isEmpty(resource)||StringUtils.isEmpty(userInfo)){
            return ResultUtils.error(CodeMsgEnum.PARAMETER_ERROR);
        }
        if (StringUtils.isEmpty(userInfo)){
            return ResultUtils.error(CodeMsgEnum.HEADER_NULL);
        }
        UserBo user  = checkBase64(userInfo);
        ResultBean resultBean = new ResultBean();
        if (map.size()>0){
            List<String> res = map.get(user.getUserId());
            if (res.contains(resource)){
                resultBean.setMsg("你有权限");
                return resultBean;
            };
        }
        resultBean.setCode(300);
        resultBean.setMsg("你没有权限");
        return resultBean;
    }

    /**
     *
     * @param resource
     *
     *
     * @return
     */
    private UserBo checkBase64(String resource) {
        UserBo bo = new UserBo();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String decode = new String(decoder.decodeBuffer(resource));
            bo = JSONObject.parseObject(decode, UserBo.class);
        } catch (Exception e) {
            e.printStackTrace();
            //日志处理或者下面这样处理出去判断
            return null;
        }
        return bo;
    }

//    public static void main(String[] args) {
//        String  resource  ="";
//        BASE64Encoder encoder = new BASE64Encoder();
//        String encode = encoder.encode(resource.getBytes());
//    }


    private void saveUser(String file, String fileName) {

        OutputStream os = null;
        InputStream inputStream  = new ByteArrayInputStream(file.getBytes());
        try {
            String path = "D:\\userFile\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
