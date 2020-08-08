package sldevcode.login.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sldevcode.login.bean.LoginBean;
import sldevcode.login.database.LoginDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
       
    
    public void init() {
    	loginDao = new LoginDao();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		try {
			if(loginDao.validate(loginBean)) {
				response.sendRedirect("welcome.jsp");
			}
			
			else {
				HttpSession session = request.getSession();
				response.sendRedirect("login.jsp");
				
			}
			
		}catch(ClassNotFoundException e) {
				e.printStackTrace();
			
		}
	}

}
