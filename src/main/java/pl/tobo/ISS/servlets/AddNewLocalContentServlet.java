/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pl.tobo.ISS.dao.ContentDao;
import pl.tobo.ISS.dao.SettingDao;
import pl.tobo.ISS.entities.Content;
import pl.tobo.ISS.entities.ContentType;
import pl.tobo.ISS.entities.GlobalSetting;
import pl.tobo.ISS.entities.User;
import pl.tobo.ISS.utils.StringConstants;

/**
 * Servlet implementation class AddNewContentServlet
 */
@WebServlet("/addNewLocalContent")
@MultipartConfig(fileSizeThreshold=1024*1024*5, // 5MB 
				 maxFileSize=1024*1024*12,      // 12MB
				 maxRequestSize=1024*1024*50)   // 50MB
public class AddNewLocalContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletUtils.userLogged(request,response)) {
			// do something
			request.getRequestDispatcher(StringConstants.ISS_VIEW_PATH+"addLocalContent.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (ServletUtils.userLogged(request,response)) {
			
			User user = (User) request.getSession().getAttribute(StringConstants.REQUEST_ATTR_LOGGED_USER);
			ContentDao dao = (ContentDao) request.getAttribute(StringConstants.REQUEST_ATTR_CONTENT_DAO);
			
			String contentTitle = request.getParameter("pictureTitle");
			String contentAddress = "";//= request.getParameter("pictureFile");
			String contentValidFrom = request.getParameter("validFrom");
			String contentValidTo = request.getParameter("validTo");

	        // gets absolute path of the web application
	        String appPath = System.getenv("OPENSHIFT_DATA_DIR");
        	System.out.println("before DB read:"+appPath);

        	SettingDao settings = (SettingDao) request.getAttribute(StringConstants.REQUEST_ATTR_SETTING_DAO);
			if(settings != null){
				GlobalSetting filePath = settings.getGlobalSettingByKey(StringConstants.SETTING_SAVE_FILE_PATH);
				if(filePath != null){
					appPath = filePath.getValue();
				}
			}
        	System.out.println("After DB read:"+appPath);
	        
	        // constructs path of the directory to save uploaded file
	        String savePath = appPath + File.separator + "content";
	    	
	        // Replaced with the dynamic one with server name.
	        // see: http://tobo.zz.mu/view.php?id=5
	        
	        String SERVER_PATH = request.getScheme() + "://"+
	        		request.getServerName() +":"+
	        		request.getServerPort();
	        
	        String CONTENT_PATH = SERVER_PATH+"/static/content/";
	        		     
	        // Creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	        if (!fileSaveDir.exists()) {
	    		fileSaveDir.mkdir();
	        }
	         
	        for (Part part : request.getParts()) {
	           	String fileName = extractFileName(part);
				if (!"".equals(fileName)) {
					contentAddress = CONTENT_PATH + fileName;
					try {
						part.write(savePath + File.separator + fileName);
					} catch (IOException ex) {
						System.out.println("DEBUG: exception: "
								+ ex.getMessage());
					}
				}
	        }
	 
			if(contentAddress != null && !"".equals(contentAddress)){
				Content c = new Content();
				c.setContentAddress(contentAddress);
				System.out.println("DEBUG: in doPost: setContentAddrress: "+contentAddress);
				if(contentTitle!=null && !"".equals(contentTitle))
					c.setTitle(contentTitle);
				
				Timestamp tFrom;
				if(contentValidFrom!=null && !"".equals(contentValidFrom)){
					contentValidFrom += " 00:00:00.000";
					System.out.println("From: "+contentValidFrom);
					tFrom = Timestamp.valueOf(contentValidFrom);
				}else{
					tFrom = new Timestamp(new Date().getTime());
				}
				System.out.println("Valid from: "+ tFrom);
				c.setValidFrom(tFrom);

				Timestamp tTo;
				if(contentValidTo!=null && !"".equals(contentValidTo)){
					contentValidTo += " 23:59:59.000";
					System.out.println("To: "+contentValidTo);
					 tTo = Timestamp.valueOf(contentValidTo);
				}
				else{
					tTo = Timestamp.valueOf("2019-12-21 23:59:59.000");
				}
				System.out.println("Valid to: "+ tTo);
				c.setValidTo(tTo);

				c.setUser(user);
				c.setContentType(ContentType.IMG);
				
				if(dao.addContent(c)){
					System.out.println("Content created: "+c.getId());
					response.sendRedirect(request.getContextPath() + "/picture?id="+ c.getId());
					
				}else{
					doGet(request, response);					
				}
			}else{
				doGet(request, response);
			}
		}
	}

	 /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
