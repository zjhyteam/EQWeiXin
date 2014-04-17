package org.earthQuake.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.earthQuake.course.jet.common.AppException;
import org.earthQuake.course.jet.common.BusinessException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class WXManageServlet extends HttpServlet {

    private ApplicationContext applicationContext=null;
    private SessionFactory sessionFactory=null;

    protected void doGet (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        if(sessionFactory==null){
            applicationContext=
                    WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            sessionFactory=((SessionFactory) applicationContext.getBean("sessionFactory"));
        }

        String eventName=(String)request.getParameter("eventName");
        if(request.getParameter("pageModel")==null){
            responPrint(response,"缺少请求信息");
            return ;
        }
        if(eventName!=null&&!"".equals(eventName)){
            String message="";
            Session session=null;
            try{
                try{
                    session=sessionFactory.getCurrentSession();
                }catch (HibernateException e){
                    session=sessionFactory.openSession();
                }
                //session=sessionFactory.openSession();
                message=doEvent(request,response,session);
                responPrint(response,message);
                
            } catch (AppException e){
                responPrint(response, "操作失败:"+e.getMessage() );
            }catch (Exception e){
                responPrint(response,e.getMessage());
            }finally {
                session.flush();
                //session.clear();
                session.close();
            }
            return ;
        }else {
            Map<String,String> map=new HashMap<String,String>();
            map.put("pageModel",request.getParameter("pageModel"));

            map.put("pageSelectedAtRuntime",request.getParameter("pageModel").replace('.','/')+".jsp");
            map.put("jsSelectedAtRuntime","page/"+request.getParameter("pageModel").replace('.','/')+".js");
            return ;
        }
    }

    @SuppressWarnings("rawtypes")
	private String doEvent(HttpServletRequest request, HttpServletResponse response,Session session)throws AppException{
        String result=null;
        try {
            Class pm=  Class.forName("org.earthQuake.course.jet.model."+request.getParameter("pageModel")+"PageModel");
            Object o=pm.newInstance();
            Field req=pm.getSuperclass().getDeclaredField("request");
            req.setAccessible(true);
            req.set(o,request);
            Field res=pm.getSuperclass().getDeclaredField("response");
            res.setAccessible(true);
            res.set(o,response);
            Field jdbc=pm.getSuperclass().getDeclaredField("session");
            jdbc.setAccessible(true);
            jdbc.set(o,session);

            result=(String)pm.getMethod(request.getParameter("eventName")).invoke(o);

        } catch (ClassNotFoundException e){
            System.out.println("请求的资源不存在"+e.getMessage());
            throw new AppException("请求的资源不存在");
        } catch (BusinessException e){
            /*e.printStackTrace();*/
            System.out.println("Exception###"+1);
            throw new AppException(e.getMessage());
        }catch (Exception e) {
            System.out.println("Exception###"+2);
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }/*catch(Throwable e){

            System.out.println("Exception###"+3);
        }*/
        return result;
    }


    private void responPrint(HttpServletResponse response,String message) throws IOException {
        //response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        printWriter.print(message);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
