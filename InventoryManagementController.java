package com.bss.controller.inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bss.databaseBean.User;
import com.bss.databaseBean.inventory.Inventory_Article;
import com.bss.databaseBean.inventory.Invt_BulkUpload;
import com.bss.databaseBean.inventory.Invt_InventoryType;
import com.bss.databaseBean.inventory.Invt_Pos_Master;
import com.bss.databaseBean.inventory.Invt_Return_Invt_Order;
import com.bss.databaseBean.inventory.Invt_WareHouse_Master;
import com.bss.service.interfaces.inventory.Inventory_Management_Service;
import com.bss.util.FileValidator;
import com.bss.util.MultiFileValidator;
import com.bss.simpleBean.DeviceList;
import com.bss.simpleBean.FileBucket;
import com.bss.simpleBean.MailSendingAPI;




	@Controller
	public class InventoryManagementController {
	
		//private static String UPLOAD_LOCATION="/opt/bssfileUpload/";
		private static String UPLOAD_LOCATION="/tmp/";

		@Autowired
		FileValidator fileValidator;

		@Autowired
		MultiFileValidator multiFileValidator;

		@InitBinder("fileBucket")
		protected void initBinderFileBucket(WebDataBinder binder) {
			binder.setValidator(fileValidator);
		}

		@InitBinder("multiFileBucket")
		protected void initBinderMultiFileBucket(WebDataBinder binder) {
			binder.setValidator(multiFileValidator);
		}
		
		@Autowired
		private Inventory_Management_Service inventory_Management_Service;
		
		@RequestMapping(value="/Inventory", method=RequestMethod.GET)
		public String posOrderRequestGet(Model model, HttpServletRequest request){
			
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 model.addAttribute("WareHouse_Master",wareHouse_Master);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("Return_Invt_Order",new Invt_Return_Invt_Order());
			 request.getSession().setAttribute("POSRequestOrderList", null);
			 request.getSession().setAttribute("ListInventoryIds", null);
			 return "POSOrder";
		}
		
		@RequestMapping(value="/InventoryReturn", method=RequestMethod.GET)
		public String posOrderReturnGet(@RequestParam String sucess,Model model, HttpServletRequest request){
			List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service(); 
			List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
		    model.addAttribute("WareHouse_Master",wareHouse_Master);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("sucess", sucess);
			 model.addAttribute("Return_Invt_Order",new Invt_Return_Invt_Order());
			 request.getSession().setAttribute("POSRequestOrderList", null);
			 request.getSession().setAttribute("ListInventoryIds", null);
			 return "POSOrder";
		}
		
		@RequestMapping(value="/ConfirmReturnFromWareHouse", method=RequestMethod.GET)
		public String ConfirmOrderFromWareHouse(@RequestParam String order_id, Model model, HttpServletRequest request){
			 String user = (String)(request.getSession().getAttribute("username"));
			 inventory_Management_Service.confirmOrderByWarehouse(order_id, user);
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 model.addAttribute("InventoryType", inventoryType);
			 request.getSession().setAttribute("POSRequestOrderList", null);
			 model.addAttribute("sucess", "Order Id- "+order_id+" Successfullly Confirm by  Warehouse.");
			 return "redirect:CreatePOSOrderSuccess";
		}
		
		
		
		@RequestMapping(value="/Inventory", method=RequestMethod.POST)
		public String posOrderRequestPost(Model model, HttpServletRequest request){
			
			User userDetail = (User) request.getSession().getAttribute("user_obj");
			
			long posId = userDetail.getPos_id();
			
			Invt_Pos_Master user = inventory_Management_Service.getInvt_Pos_Master(posId);
			
			
			@SuppressWarnings("unchecked")
			List<String> item = (List<String>)(request.getSession().getAttribute("POSRequestOrderList"));
			inventory_Management_Service.setPos_Request_Order(user,item);
			model.addAttribute("sucess", "Your Order succussfully Requested to WareHouse.");
			String from = "gaganyadav1992@gmail.com";
	        String pass = "Gagan@123";
	        String to = "gaganyadav1992@gmail.com";
	        String CCMailID = "gaganyadav1992@gmail.com";
	        String subject = "Request order";
	        String message = "You get the oder......";
			MailSendingAPI  sendmaiobj=new MailSendingAPI();
			sendmaiobj.MailDepartment(from, pass, to, CCMailID, subject, message);
			return "redirect:InventorySuccess";
		}
		
		
		@RequestMapping(value="/WareHousePushInventory", method=RequestMethod.POST)
		public String wareHousePushInventory(@ModelAttribute("POSID") DeviceList device, Model model, HttpServletRequest request){
			
			//String username = (String) request.getSession().getAttribute("username");
			String posId = device.getList();
			Invt_Pos_Master user = null;
			posId = posId == null ? "" : posId;
			System.out.println(">>>>>>>>>>>>>>>[WareHousePushInventory]>>>>>>>>[POS]>>>>>>>>>>"+posId);
			
			if(!posId.equals("")){
				user = inventory_Management_Service.getInvt_Pos_Master(Long.parseLong(posId));
				String item = (String) request.getSession().getAttribute("ListInventoryIds");
				System.out.println(">>>>>>>>>>>>>>>[WareHousePushInventory]>>>>>>>>[ITEM]>>>>>>>>>>"+item);
				inventory_Management_Service.setWarehouse_ReqPush_Order(user,item);
				model.addAttribute("sucess", "Warehouse Sucessfully push the Inventory.");
			return "redirect:CreatePOSOrderSuccess";
			}else{
				model.addAttribute("sucess", "Please Select The POS Id.");
				return "redirect:CreatePOSOrderSuccess";
				/*
				@SuppressWarnings("unchecked")
				List<String> item = (List<String>)(request.getSession().getAttribute("POSRequestOrderList"));
				inventory_Management_Service.setPos_Request_Order(user,item);
				model.addAttribute("sucess", "Your Order succussfully Requested to WareHouse.");
				String from = "gaganyadav1992@gmail.com";
		        String pass = "Gagan@123";
		        String to = "gaganyadav1992@gmail.com";
		        String CCMailID = "gaganyadav1992@gmail.com";
		        String subject = "Request order";
		        String message = "You get the oder......";
				MailSendingAPI  sendmaiobj=new MailSendingAPI();
				sendmaiobj.MailDepartment(from, pass, to, CCMailID, subject, message);
				return "redirect:InventorySuccess";*/
				
			}
			
		}
		
		@RequestMapping(value="/InventorySuccess", method=RequestMethod.GET)
		public String posOrderRequestGetSuccess(@RequestParam String sucess, Model model, HttpServletRequest request){
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("sucess", sucess);
			 request.getSession().setAttribute("POSRequestOrderList", null);
			 return "POSOrder";
		}
		
		
		@RequestMapping(value="/CreatePosOrder", method=RequestMethod.GET)
		public String createPosOrderGet(Model model, HttpServletRequest request){
			
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 //inventory_Management_Service.getInvt_Pos_OrderCriteriaSearch("","","", "2016-08-03", "2016-08-03");
			// List<Invt_Pos_Master> invt_Pos_MasterList = inventory_Management_Service.getInvt_Pos_MasterList();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service(); 
			 List<Invt_Pos_Master> pos_master = inventory_Management_Service.getInvt_Pos_MasterList();
			 request.getSession().setAttribute("ListInventoryIds", null);
			 model.addAttribute("POS_Master", pos_master);
			 model.addAttribute("POSID", new DeviceList());
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 //model.addAttribute("Invt_Pos_MasterList", invt_Pos_MasterList);
			 request.getSession().setAttribute("CreatePosOrderList", null);
			 return "CreatePOSOrder";
		}
		
		@RequestMapping(value="/CreatePosOrder", method=RequestMethod.POST)
		public String createPosOrderPost(Model model, HttpServletRequest request){
			
		
			 @SuppressWarnings("unchecked")
			 List<String> item = (List<String>)(request.getSession().getAttribute("CreatePosOrderList"));
			 String user = (String)(request.getSession().getAttribute("username"));
			 try{
				 inventory_Management_Service.setCreatePosOrder(item, user);
				 model.addAttribute("sucess", "Your Order succussfully Dispatched.");
			 }catch(Exception ex){
				 model.addAttribute("sucess", "Your Order not Dispatched.");
			 }
			
			
			 return "redirect:CreatePOSOrderSuccess";
		}
		
		
		
		@RequestMapping(value="/ListInventoryItemsReturn", method=RequestMethod.POST)
		public String listInventoryItemsReturn(@ModelAttribute("Return_Invt_Order") Invt_Return_Invt_Order return_Invt_Order,Model model, HttpServletRequest request){
			
		
			 
			 String item = (String)(request.getSession().getAttribute("ListInventoryIds"));
			 String user = (String)(request.getSession().getAttribute("username"));
			 User userDetail = (User) request.getSession().getAttribute("user_obj");
			 long posId = userDetail.getPos_id();
			 
			 try{
				 inventory_Management_Service.setReturnInventory(item, return_Invt_Order,user,posId);
				 model.addAttribute("sucess", "Your Order succussfully Returned.");
			 }catch(Exception ex){
				 model.addAttribute("sucess", "Your Order not Returned.");
			 }
			
			
			 return "redirect:InventoryReturn";
		}
		
		@RequestMapping(value="/ConfirmOrderFromPos", method=RequestMethod.GET)
		public String ConfirmOrderFromPos(@RequestParam String order_id, Model model, HttpServletRequest request){
			 String user = (String)(request.getSession().getAttribute("username"));
			 inventory_Management_Service.confirmOrderByPos(order_id, user);
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 model.addAttribute("InventoryType", inventoryType);
			 request.getSession().setAttribute("POSRequestOrderList", null);
			 model.addAttribute("sucess", "Order Id- "+order_id+" Successfullly Recieved by  POS.");
			 return "POSOrder";
		}
		
		@RequestMapping(value="/CreatePosOrderFilled", method=RequestMethod.GET)
		public String createPosOrderGet1(@RequestParam String pos_id, @RequestParam String order_id, Model model, HttpServletRequest request){
			
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_Pos_Master> invt_Pos_MasterList = inventory_Management_Service.getInvt_Pos_MasterList();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("pos_id", pos_id);
			 model.addAttribute("order_id", order_id);
			 model.addAttribute("Invt_Pos_MasterList", invt_Pos_MasterList);
			 request.getSession().setAttribute("CreatePosOrderList", null);
			 
			 return "CreatePOSOrder";
		}
		
		@RequestMapping(value="/CancelPosOrder", method=RequestMethod.GET)
		public String CancelPosOrder(@RequestParam String pos_id,@RequestParam String order_id, Model model, HttpServletRequest request){
			
			 String user = (String)(request.getSession().getAttribute("username"));
			 inventory_Management_Service.cancelPosOrder(order_id, user);

			 model.addAttribute("sucess", "POSOrder  Id - "+order_id+" Cancelled.");
				
			 return "redirect:CreatePOSOrderSuccess";
			 
		}
		
		@RequestMapping(value="/CreatePosOrderFilled", method=RequestMethod.POST)
		public String createPosOrderPOST(Model model, HttpServletRequest request){
			 
			@SuppressWarnings("unchecked")
			List<String> item = (List<String>)(request.getSession().getAttribute("CreatePosOrderList"));
			String user = (String)(request.getSession().getAttribute("username"));
			try{
				inventory_Management_Service.setCreatePosOrder(item, user);
				model.addAttribute("sucess", "Your Order succussfully Dispatched CreatePosOrderFilled.");
			}catch(Exception ex){
				model.addAttribute("sucess", "Your Order not Dispatched CreatePosOrderFilled.");
				
			}
			
			
			return "redirect:CreatePOSOrderSuccess";
			
		}
		
		@RequestMapping(value="/CreatePOSOrderSuccess", method=RequestMethod.GET)
		public String createPOSOrderSuccessGet(@RequestParam String sucess,Model model, HttpServletRequest request){
			
			 /*List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("sucess", sucess);
			 request.getSession().setAttribute("CreatePosOrderList", null);
			 return "CreatePOSOrder";*/
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service(); 
			 List<Invt_Pos_Master> pos_master = inventory_Management_Service.getInvt_Pos_MasterList();
			 request.getSession().setAttribute("ListInventoryIds", null);
			 model.addAttribute("POSID", new DeviceList());
			 model.addAttribute("POS_Master", pos_master);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("sucess", sucess);
			 request.getSession().setAttribute("CreatePosOrderList", null);
			 return "CreatePOSOrder";
			 
		}
		
		@RequestMapping(value="/CreateArticle", method=RequestMethod.GET)
		public String CreateArticle(Model model, HttpServletRequest request){
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service(); 
			 List<Invt_Pos_Master> posMaster = inventory_Management_Service.getInvt_Pos_MasterList();
			 FileBucket fileModel = new FileBucket();
			 model.addAttribute("fileBucket", fileModel);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("InvtArticle", new Inventory_Article());
			 model.addAttribute("Warehouse", new Invt_WareHouse_Master());
			 model.addAttribute("POSMaster", posMaster);
			 
			 return "CreateArticle";
		}
		
		@RequestMapping(value = "/ArticleBulkUpload", method = RequestMethod.POST)
		public ModelAndView singleFileUpload(@Valid FileBucket fileBucket, BindingResult result,ModelAndView modelAndView, Model model,RedirectAttributes redir,  HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String line;
			User user = (User) request.getSession().getAttribute("user_obj");
			String checkFile = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();	
			String currentDate = dateFormat.format(date);
			Invt_BulkUpload invt_BulkUpload = new Invt_BulkUpload();
    		
			String errorFileNamePath= UPLOAD_LOCATION+"bulkuploaderrorFile/";
			String errorFileName = errorFileNamePath+fileBucket.getFile().getOriginalFilename().replaceAll(".txt", "_")+"error.txt";
			
	
    		PrintWriter pw =  new PrintWriter(new File(errorFileName));
             try{
	             if (result.hasErrors()) {
					System.out.println("validation errors");
					model.addAttribute("sucess", "Please insert File.");
					//fw.close();
					pw.close();
					modelAndView.setViewName("CreateArticle");
					return modelAndView;
				} else {
					System.out.println("Fetching file");
					MultipartFile multipartFile = fileBucket.getFile();
					//response.addHeader("Content-Disposition", "attachment; filename="+multipartFile.getOriginalFilename());
					InputStream inputStream = multipartFile.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					if (!multipartFile.isEmpty()) {			
						
						while ((line = bufferedReader.readLine()) != null){
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>LINE>>>>>>>>>>>>>>>>>>>"+line);  
							String[] columns = line.split(",");
							Inventory_Article invt_Art = new Inventory_Article();
		            	 
		            	try{
			            	 invt_Art.setArticle_number(columns[10]);
			            	 invt_Art.setDevice_barcode(columns[9]);
			            	 invt_Art.setDevice_brand(columns[2]);
			            	 invt_Art.setDevice_model_no(columns[3]);
			            	 invt_Art.setDevice_name(columns[1]);
			            	 invt_Art.setDevice_os(columns[6]);
			            	 invt_Art.setDevice_ram(columns[5]);
			            	 invt_Art.setDevice_price(columns[7]);
			            	 invt_Art.setDevice_storage(columns[4]);
			            	 invt_Art.setDevice_warrantry_period(columns[8]);
			            	 invt_Art.setInventory_id(Long.parseLong(columns[0].trim()));
			            	 invt_Art.setUser(user.getUsername());
			            	 
			            	 Invt_WareHouse_Master wareHouse = new Invt_WareHouse_Master();
			            	 wareHouse.setWarehouse_id(Long.parseLong(columns[11].trim()));
			            	
		            		 inventory_Management_Service.setInventory_Article(invt_Art, wareHouse,user);
		            		 
		            	 }catch(ConstraintViolationException ex){
		            		
		            		String error = ex.getCause()+"";
		            		String[] errormsg = error.split(":");
		            		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>ConstraintViolationException>>>>>>>>>>>>>"+errormsg[1]);
		                     pw.println(line+", //"+errormsg[1]);
		                     checkFile = "error";
		            		 continue;
		            	 }catch(NumberFormatException e){
		            		 String error = e.initCause(e.getCause())+"";
		            		 String[] errormsg = error.split(":");
		            		 pw.println(line+", //"+errormsg[1]+" "+errormsg[2]);
		            		 checkFile = "error"; 
		            		continue;
		            	 } catch(Exception ex){
		            		 String error = ex.getCause()+"";
			            	 String[] errormsg = error.split(":");
		            		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>Exception>>>>>>>>>>>>>"+ex.getCause());
		            		 pw.println(line+", //"+errormsg[1]);
		            		 checkFile = "error";
		            		 continue;
		            	 }
		            	
					  
					}
					
						String fileName = multipartFile.getOriginalFilename();						
						invt_BulkUpload.setBulkupload_date(currentDate);
						invt_BulkUpload.setBulkupload_fileid(fileName);
						invt_BulkUpload.setBulkupload_path(UPLOAD_LOCATION);
						invt_BulkUpload.setBulkupload_user(user.getUsername());
						invt_BulkUpload.setBulkupload_errorFile_path(errorFileNamePath);
						invt_BulkUpload.setBulkupload_errorFile_name(errorFileName);
						
						FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File( UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
						pw.close();
						System.out.println(">>>>>>>>>>>>>>>>file>>>>>>>>>>>>>"+fileName);
			             inventory_Management_Service.saveBulkUploadFile(invt_BulkUpload);
			             redir.addFlashAttribute("sucess", "Article Sucessfully Added.");
			             modelAndView.setViewName("redirect:CreateDeleteArticle");
						if(checkFile!=null){
							throw new IOException();
						}
					 }
					 
				}
			}catch(IOException ex){
				invt_BulkUpload.setBulkupload_errorFile_name(errorFileName);
				redir.addFlashAttribute("id", invt_BulkUpload.getBulkupload_id()+"");
				modelAndView.setViewName("redirect:CreateDeleteArticle");
	 		    redir.addFlashAttribute("sucess", "Some Article are not Sucessfully Added, Please Download error file.");
				pw.flush();
				pw.close();
				
				 
			}
           
             
            
            
             return modelAndView;
		}
		
		
		
		
		@RequestMapping(value="/CreateArticle", method=RequestMethod.POST)
		public String CreateArticlePost(@ModelAttribute("InvtArticle") Inventory_Article invt_Article , 
										@ModelAttribute("Warehouse") Invt_WareHouse_Master invt_wareHouse,
										Model model, HttpServletRequest request){
			
			  User user = (User) request.getSession().getAttribute("user_obj");
			inventory_Management_Service.setInventory_Article(invt_Article, invt_wareHouse,user);
			
			model.addAttribute("sucess", "Article Sucessfully Added.");
			 
			return "redirect:CreateDeleteArticle";
		}
		
		@RequestMapping(value= {"/edit-Article-{id}"}, method=RequestMethod.GET)
		public String EditArticle(@PathVariable String id, Model model, HttpServletRequest request){
			 
			List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			Inventory_Article inventoryArt = inventory_Management_Service.getInventory_ArticlebyId(id);  
			model.addAttribute("InvtArticle", inventoryArt);
			model.addAttribute("WareHouse_Master", wareHouse_Master);
			model.addAttribute("InventoryType", inventoryType);
			model.addAttribute("Warehouse", new Invt_WareHouse_Master());
			
			return "EditArticle";
		}
		
		@RequestMapping(value= {"/edit-Article-{id}"}, method=RequestMethod.POST)
		public String EditArticlePost(@ModelAttribute("CustomerContact")Inventory_Article invt_Article,
									  @ModelAttribute("Warehouse")Invt_WareHouse_Master invt_WareHouse,
									  @PathVariable String id, Model model, HttpServletRequest request){
			
			
			String user = (String)(request.getSession().getAttribute("username"));
			inventory_Management_Service.updateInventory_Article(invt_Article,invt_WareHouse, user);
			model.addAttribute("sucess", "Article Id- "+id+" Sucessfully Edited.");
			return "redirect:CreateDeleteArticle";
			
	
		}
		
		@RequestMapping(value= {"/delete-Article-{id}"}, method=RequestMethod.GET)
		public String DeleteArticle(@PathVariable String id, Model model, HttpServletRequest request){
			
			String user = (String)(request.getSession().getAttribute("username"));
			inventory_Management_Service.deleteInventory_Article(id, user);
			model.addAttribute("sucess", "Article Id- "+id+" Sucessfully Deleted.");
			return "redirect:CreateDeleteArticle";
		}
		
		@RequestMapping(value="/CreateDeleteArticle", method=RequestMethod.GET)
		public String CreateDeleteArticle(@ModelAttribute("sucess") String sucess,@ModelAttribute("id") String id, Model model, HttpServletRequest request){
			
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 String some = (String) model.asMap().get("sucess");
			 FileBucket fileModel = new FileBucket();
			 model.addAttribute("fileBucket", fileModel);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("sucess", some);
			 
			 return "CreateArticle";
		}
		
		@RequestMapping(value="/BulkUploadResult", method=RequestMethod.GET)
		public String BulkUploadResult(@ModelAttribute("id") String id, Model model, HttpServletResponse response, HttpServletRequest request){
			
			 List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			 List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();  
			 String some = (String) model.asMap().get("id");
			 System.out.println(">>>>>>>>>>>>>>>>>>IDDDDDDDDDDD>>>>>>>>>>"+some);
			 
			 FileBucket fileModel = new FileBucket();
			 model.addAttribute("fileBucket", fileModel);
			 model.addAttribute("InventoryType", inventoryType);
			 model.addAttribute("WareHouse_Master", wareHouse_Master);
			 model.addAttribute("sucess", some);
			 getFile(response, request, some );
			 return "CreateArticle";
		}
		@RequestMapping(value="/ListArticle", method=RequestMethod.GET)
		public String ListArticle(Model model, HttpServletRequest request){
			
			List<Invt_WareHouse_Master> wareHouse_Master = inventory_Management_Service.getInvt_WareHouse_Master();
			List<Invt_InventoryType> inventoryType = inventory_Management_Service.getinventory_Management_Service();
			List<Invt_Pos_Master> invt_Pos_MasterList = inventory_Management_Service.getInvt_Pos_MasterList();
			model.addAttribute("InventoryType", inventoryType);
			model.addAttribute("WareHouse_Master", wareHouse_Master);
			model.addAttribute("invt_Pos_MasterList", invt_Pos_MasterList);
			 
			return "ListArticle";
		}
		
		@RequestMapping(value="/createPos", method=RequestMethod.GET)
		public String createPosMaster(Model model, HttpServletRequest request){
			
			model.addAttribute("Invt_Pos_Master", new Invt_Pos_Master());
			
			 
			return "createPosMaster";
		}
		
		@RequestMapping(value="/createPos", method=RequestMethod.POST)
		public String createPosMasterPost(@ModelAttribute("WareHousesMaster")Invt_Pos_Master pos_Master, Model model, HttpServletRequest request){
			
			
			inventory_Management_Service.addPosMaster(pos_Master);
			model.addAttribute("sucess","POS "+pos_Master.getPos_name()+" Successfully added.");
			 
			return "createPosMaster";
		}
		
		@RequestMapping(value="/createWareHouse", method=RequestMethod.GET)
		public String createWareHouseMaster(Model model, HttpServletRequest request){
			
			
			model.addAttribute("WareHousesMaster", new Invt_WareHouse_Master());
			
			 
			return "createWareHouseMaster";
		}
		
		@RequestMapping(value="/createWareHouse", method=RequestMethod.POST)
		public String createWareHouseMasterPost(@ModelAttribute("WareHousesMaster")Invt_WareHouse_Master invt_WareHouse, Model model, HttpServletRequest request){
			
			inventory_Management_Service.addWareHouse(invt_WareHouse);
			model.addAttribute("sucess","Warehouse "+invt_WareHouse.getWarehouse_name()+" Successfully added.");
			
			 
			return "createWareHouseMaster";
		}
		
		@RequestMapping(value="/SampleFile", method=RequestMethod.GET)
		public void getSampleFile( HttpServletResponse response, HttpServletRequest request) {
		    try {
			    	 String contextpath=request.getContextPath();
			    	 String line = null;
			    	// String ResourcePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			    	 String fileName = "sampleFile.txt";
			    	 ClassLoader classLoader = getClass().getClassLoader();
			    	 File file = new File(classLoader.getResource(fileName).getFile());

			    	 //File file = new File(fileName);
				     FileWriter fw = new FileWriter(contextpath); 
			         PrintWriter pw =  response.getWriter();
				     response.addHeader("Content-Disposition", "attachment; filename="+file.getName());
		    	
				     InputStream is = new FileInputStream(file);
				     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
				     while ((line = bufferedReader.readLine()) != null){
				    	 pw.println(line);
				     }
				     
				     bufferedReader.close();
				     pw.flush();
				     pw.close();
				     fw.close();
		    	} catch (IOException ex) {
			      System.out.println("Error writing file to output stream. Filename was '{}'"+ ex);
			      throw new RuntimeException("IOError writing file to output stream");
		    }

		}
		
		public void getFile( HttpServletResponse response, HttpServletRequest request, String id) {
		    try {
			    	 String contextpath=request.getContextPath();
			    	 String line = null;
			    	 Invt_BulkUpload invt_BulkUpload = inventory_Management_Service.getInvt_BulkUploadById(id);
			    	 String fileName = invt_BulkUpload.getBulkupload_errorFile_name();
			    	 File file = new File(fileName);
				     FileWriter fw = new FileWriter(contextpath); 
			         PrintWriter pw =  response.getWriter();
				     response.addHeader("Content-Disposition", "attachment; filename="+file.getName());
		    	
				     InputStream is = new FileInputStream(file);
				     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
				     while ((line = bufferedReader.readLine()) != null){
				    	 pw.println(line);
				     }
				     
				     bufferedReader.close();
				     pw.flush();
				     pw.close();
				     fw.close();
		    	} catch (IOException ex) {
				      System.out.println("Error writing file to output stream. Filename was '{}'"+ ex);
				      throw new RuntimeException("IOError writing file to output stream");
		    }

		}
		
	}
