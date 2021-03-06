package com.cos.shop.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.shop.action.Action;
import com.cos.shop.model.Product;
import com.cos.shop.repository.ProductRepository;
import com.cos.shop.util.Script;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminRegisterProductAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = request.getServletContext().getRealPath("/prodImages");
		String contextPath = request.getContextPath();
		
		int size = 10*1024*1024;
		
		
		String category = "";
		String name = "";
		String price = "";
		// String quantity = "";
		String description = "";
		
		String filename1 = "";
		String origfilename1 = "";
		String filepath1 = "";
		
		String filename2 = "";
		String origfilename2 = "";
		String filepath2 = "";
		
		String filename3 = "";
		String origfilename3 = "";
		String filepath3 = "";
		
		try {
			MultipartRequest multi = new MultipartRequest(request,
							uploadPath,
							size,
							"UTF-8",
							new DefaultFileRenamePolicy());
			
			category = multi.getParameter("subCategory");
			name = multi.getParameter("prodName");
			price = multi.getParameter("price");
			// quantity = multi.getParameter("quantity");
			description = multi.getParameter("description");
			
			filename1 = multi.getFilesystemName("file1");
			origfilename1 = multi.getOriginalFileName("file1");
			filename2 = multi.getFilesystemName("file2");
			origfilename2 = multi.getOriginalFileName("file2");
			filename3 = multi.getFilesystemName("file3");
			origfilename3 = multi.getOriginalFileName("file3");
			
/*			
			filepath1 = uploadPath + "\\" + filename1;
			filepath2 = uploadPath + "\\" + filename2;
			filepath3 = uploadPath + "\\" + filename3;
*/			
			filepath1 = contextPath + "/prodImages/" + filename1;
			filepath2 = contextPath + "/prodImages/" + filename2;
			filepath3 = contextPath + "/prodImages/" + filename3;
			
			Product product = Product.builder()
					.category_id(Integer.parseInt(category))
					.name(name)
					.price(Integer.parseInt(price))
					.description(description)
					.image1(filepath1)
					.image2(filepath2)
					.image3(filepath3)
					.build();
			
			
			ProductRepository productRepository = ProductRepository.getInstance();
			int result = productRepository.save(product);
			
			System.out.println("상품등록 결과 : result :" + result);
			
			if (result == 1) {
				Script.href("상품등록 성공", "index.jsp", response);
			} else {
				Script.back("상품등록 실패", response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
