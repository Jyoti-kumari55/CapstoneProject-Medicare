package com.example.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.example.controller.FactoryProvider;
import com.example.dao.CategoryDao;
import com.example.dao.ProductDao;
import com.example.entities.Category;
import com.example.entities.Product;

/**
 * Servlet implementation class ProductOperationServlet
 */
@WebServlet("/ProductOperationServlet")
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

//            servlet 2:
//            add category 
//            add product
            String op = request.getParameter("operation");

            if (op.trim().equals("addcategory")) {
                //add category
                //fetching category data
                String title = request.getParameter("catTitle");
                String description = request.getParameter("catDescription");

                Category category = new Category();
                category.setCategoryTitle(title);
                category.setCategoryDescription(description);

                //category databse save:
                CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                int catId = categoryDao.saveCategory(category);
//                out.println("Category Saved");
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Category added successfully : " + catId);
                response.sendRedirect("admin.jsp");
                return;

            } else if (op.trim().equals("addproduct")) {
                //add product
                //work
                String pName = request.getParameter("pName");
                String pDesc = request.getParameter("pDesc");
                int pPrice = Integer.parseInt(request.getParameter("pPrice"));
                int pDiscount = Integer.parseInt(request.getParameter("pDiscount"));
                int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
                int catId = Integer.parseInt(request.getParameter("catId"));
                Part part = request.getPart("pPic");

                Product p = new Product();
                p.setpName(pName);
                p.setpDesc(pDesc);
                p.setpPrice(pPrice);
                p.setpDiscount(pDiscount);
                p.setpQuantity(pQuantity);
                p.setpPhoto(part.getSubmittedFileName());

                //get category by id
                CategoryDao cdoa = new CategoryDao(FactoryProvider.getFactory());
                Category category = cdoa.getCategoryById(catId);

                p.setCategory(category);

                //product save...
                ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
                pdao.saveProduct(p);

                //pic upload
//                find out the path to upload photo
                String path = request.getRealPath("img") + File.separator + "products" + File.separator + part.getSubmittedFileName();
                System.out.println(path);

                //uploading code..
                try {

                    FileOutputStream fos = new FileOutputStream(path);

                    InputStream is = part.getInputStream();

//                reading data
                    byte[] data = new byte[is.available()];

                    is.read(data);

//                writing the data
                    fos.write(data);

                    fos.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                out.println("Product save sucess...");
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Product is added successfully..");
                response.sendRedirect("admin.jsp");
                return;

            }

        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
