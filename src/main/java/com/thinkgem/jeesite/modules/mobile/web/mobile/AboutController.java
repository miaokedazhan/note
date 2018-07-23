package com.thinkgem.jeesite.modules.mobile.web.mobile;

import com.thinkgem.jeesite.modules.mobile.entity.DmApk;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author 刘智科
 * 关于
 */
@Controller
@RequestMapping(value = "/about")
public class AboutController {

    @RequestMapping(value="/aboutUse", produces="application/pdf")
    public ResponseEntity<byte[]> aboutUse(HttpServletRequest request, HttpServletResponse resp) {
        String path = request.getSession().getServletContext().getRealPath("/") + "/userfiles/file/使用指引.pdf";
        String fileName = "使用指引.pdf";
        File f=new File(path);
        InputStream in =null;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes(),"utf-8");
            //MediaType mt = new MediaType("application/vnd.android.package-archive");
            //headers.setContentType(mt);
            //以下的类型必须要设置，要不不能在android机上正常下载的
            headers.add("Content-Disposition", "attachment;filename="+fileName);

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
    @RequestMapping(value="/aboutHaoXie", produces="application/pdf")
    public ResponseEntity<byte[]> aboutHaoXie(HttpServletRequest request, HttpServletResponse resp) {
        String path = request.getSession().getServletContext().getRealPath("/") + "/userfiles/file/关于好写.pdf";
        String fileName = "关于好写.pdf";
        File f=new File(path);
        InputStream in =null;
        ResponseEntity<byte[]> response=null ;
        try {
            in = new FileInputStream(f);
            byte[] b=new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes(),"utf-8");
            //MediaType mt = new MediaType("application/vnd.android.package-archive");
            //headers.setContentType(mt);
            //以下的类型必须要设置，要不不能在android机上正常下载的
            headers.add("Content-Disposition", "attachment;filename="+fileName);

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
