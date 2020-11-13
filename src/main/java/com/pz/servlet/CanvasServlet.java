package com.pz.servlet;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class CanvasServlet
 */
//@CrossOrigin
@Controller
@RequestMapping("/anvasServlet")
//@WebServlet(name = "CanvasServlet",urlPatterns="/CanvasServlet")
public class CanvasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CanvasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @RequestMapping("/baseCes")
    @ResponseBody
    protected  void doPost(HttpServletRequest request, HttpServletResponse response,String imedata) throws ServletException, IOException {
        // TODO Auto-generated method stub
        /*
         * response.setHeader("Access-Control-Allow-Origin", "origin");
         * response.setHeader("Access-Control-Allow-Methods",
         * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
         * "3600"); response.setHeader("Access-Control-Allow-Headers",
         * "x-requested-with,Authorization,token, content-type"); //这里要加上content-type
         * response.setHeader("Access-Control-Allow-Credentials", "true");
         */
        //String base64Data = imedata.toString();
        String base64Data = request.getParameter("imedata");
        //String base64Data = imedata;
        //System.out.println(base64Data);
        if(base64Data!=null){
            BASE64Decoder deoder = new BASE64Decoder();
            try {
                byte[] b = deoder.decodeBuffer(base64Data);
                for(int i = 0 ; i<b.length;i++){
                    if(b[i]<0){//调整异常数据
                        b[i]+=256;
                    }
                }
                File f = new File("d://canvas");
                if(!f.exists()){
                    f.mkdir();
                }
                //生成图片
                String filepath = "d://canvas//1.png";

                OutputStream out1 = new FileOutputStream(filepath);
                out1.write(b);
                out1.flush();
                out1.close();
                rs(response,"true");
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                rs(response,"false");
            }
        }else{
            rs(response,"false");
        }

    }

    public void rs(HttpServletResponse response,String rs){
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(rs);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}