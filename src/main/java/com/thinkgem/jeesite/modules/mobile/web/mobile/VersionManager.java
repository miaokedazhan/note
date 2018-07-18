package com.thinkgem.jeesite.modules.mobile.web.mobile;


import com.thinkgem.jeesite.modules.mobile.entity.DmApk;
import com.thinkgem.jeesite.modules.mobile.service.DmApkService;
import com.thinkgem.jeesite.modules.mobile.utils.MobileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘智科
 * @Title: $file_name
 * @Package $package_name
 * @Description: $todo
 */
@Controller
@RequestMapping(value = "/version")
public class VersionManager {

    @Autowired
    private DmApkService dmApkService;

    @RequestMapping(value = {"getApk"})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        DmApk dmApk = dmApkService.getNewApkForAndroid("com.wlzn.nazapad");
        model.addAttribute("dmApk", dmApk);
        return "modules/mobile/downLoad";
    }

    @RequestMapping(value = {"getApkList"})
    public String getApkList(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<DmApk> dmApks = dmApkService.getApkList("com.wlzn.nazapad");
        model.addAttribute("dmApks", dmApks);
        return "modules/mobile/downLoadList";
    }

    @ResponseBody
    @RequestMapping(value = {"validateVersion"})
    public MobileResult validateVersion(String os, String version,String packagename) {
        DmApk dmApk = null;
        Map<String, Object> map = new HashMap<String, Object>();
        if (("Android").equals(os)) {
            dmApk = dmApkService.getNewApkForAndroid(packagename);
            if(dmApk==null){
                return   MobileResult.ok("版本不存在","");
            }
            if (dmApk.getVersion().equals(version)) {
                map.put("isNew", true);
                map.put("url", "is new");
                map.put("version", dmApk.getVersion());
            } else {
                map.put("isNew", false);
                map.put("url", "/version/apkDownload");
                map.put("version", dmApk.getVersion());
            }
        } else if (("iOS").equals(os)) {
            Map<String,Object> maps = dmApkService.isAvailableForIos();
            String available = maps.get("available").toString();
            if(available.equals("0")) {
                dmApk = dmApkService.getNewApkForIos(packagename);
                if (dmApk == null) {
                    return MobileResult.ok("版本不存在", "");
                }
                if (dmApk.getVersion().equals(version)) {
                    map.put("status", 0);
                    map.put("version", dmApk.getVersion());
                } else {
                    map.put("status", 1);
                    map.put("version", dmApk.getVersion());
                }
            }else{
                map.put("status", 2);
                map.put("version", "");
            }
        } else {
            map.put("isNew", true);
            map.put("url", "");
            map.put("version", version);
        }
        return MobileResult.ok("验证版本成功", map);
    }

      @RequestMapping(value="/apkDownload", produces="application/vnd.android.package-archive")
      public ResponseEntity<byte[]> testDownload(HttpServletRequest request, HttpServletResponse resp){
                DmApk  dmApk = dmApkService.getNewApkForAndroid("com.wlzn.nazapad");
                String path=request.getSession().getServletContext().getRealPath("/")+dmApk.getUrl();
                File f=new File(path);
                 String filename = f.getName();
                InputStream in =null;
                ResponseEntity<byte[]> response=null ;
                try {
                       in = new FileInputStream(f);
                       byte[] b=new byte[in.available()];
                       in.read(b);
                       HttpHeaders headers = new HttpHeaders();
                        filename = new String(filename.getBytes(),"utf-8");
                         //MediaType mt = new MediaType("application/vnd.android.package-archive");
                        //headers.setContentType(mt);
                    //以下的类型必须要设置，要不不能在android机上正常下载的
                         headers.add("Content-Disposition", "attachment;filename="+filename);

                        //resp.setContentType("application/vnd.android.package-archive");
                        HttpStatus statusCode=HttpStatus.OK;
                        response = new ResponseEntity<byte[]>(b, headers, statusCode);
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally{
                         try {
                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                             }

                     }
                return response;
            }

}
