package com.bss.dao.implementingClasses.inventory;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bss.dao.AbstractDao;
import com.bss.dao.interfaces.inventory.Inventory_Management_Dao;
import com.bss.databaseBean.inventory.Inventory_Article;
import com.bss.databaseBean.inventory.Invt_Esnnumber_Blacklist;
import com.bss.databaseBean.inventory.Invt_InventoryType;
import com.bss.simpleBean.DeviceList;

@Repository("Inventory_Management_Dao")
public class Inventory_Management_DaoImpl extends AbstractDao<Long,Inventory_Article> implements Inventory_Management_Dao{

	private Logger logger = Logger.getLogger(Inventory_Management_DaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<DeviceList> getInventory_Article(String warehouseId, String id,String brand,String status){
	   
		 id = id == null ? "" : id;
		 brand = brand == null ? "" : brand;
		 warehouseId = warehouseId == null ? "" : warehouseId;
		 status = status == null ? "" : status;
		 
		Criteria criteria = getSession().createCriteria(Inventory_Article.class).createAlias("invt_WareHouse_Master","WareHouse_Master");
		
		if(!status.equals(""))
			criteria.add(Restrictions.eq("status",status));

		if(brand.equals("")) {
			
			if(!id.equals(""))
				criteria.add(Restrictions.eq("inventory_id",Long.parseLong(id)));
			
			if(!warehouseId.equals(""))
				criteria.add(Restrictions.eq("WareHouse_Master.warehouse_id",Long.parseLong(warehouseId)));
			 
			criteria.setProjection(Projections.distinct(Projections.projectionList()
				.add(Projections.property("device_brand"), "list") ));
		}
	    
	    if(!id.equalsIgnoreCase("") && !brand.equalsIgnoreCase("")){ 
	    	criteria.add(Restrictions.eq("inventory_id",Long.parseLong(id)));
	    	if(!warehouseId.equals(""))
	    		criteria.add(Restrictions.eq("WareHouse_Master.warehouse_id",Long.parseLong(warehouseId)));
	    	criteria.add(Restrictions.eq("device_brand",brand));
	    	
	    	criteria.setProjection(Projections.distinct(Projections.projectionList()
	    		    .add(Projections.property("device_model_no"), "list") ));
	    }
	    List<DeviceList> list =  criteria.setResultTransformer(Transformers.aliasToBean(DeviceList.class)).list();
	    return list;
	  }
	
	@SuppressWarnings("unchecked")
	public List<Invt_InventoryType> getinventory_Management_Service(){
		
		Criteria criteria = getSession().createCriteria(Invt_InventoryType.class);
		
		return (List<Invt_InventoryType>)criteria.list();
	}
	
	public Invt_InventoryType getinventory_ManagementById(String id){
		Criteria criteria = getSession().createCriteria(Invt_InventoryType.class);
		criteria.add(Restrictions.eq("inventory_id",Long.parseLong(id)));
		return (Invt_InventoryType)criteria.uniqueResult();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Inventory_Article> getInventory_ArticleList(String warehouseId, String ItemType, String brand, String model){
		warehouseId = warehouseId == null ? "" : warehouseId;
	    brand = brand == null ? "" : brand;
	    ItemType = ItemType == null ? "" : ItemType;
	    model = model == null ? "" : model;
	    long y = 2;
	    long x= 1;
		Criteria criteria = getSession().createCriteria(Inventory_Article.class,"InventoryArticle")
			    						.createAlias("InventoryArticle.invt_WareHouse_Master","wareHouse")
			    						.add(Restrictions.eq("InventoryArticle.device_brand", "Apple"))
			    						.add(Restrictions.eq("wareHouse.warehouse_id", x));
		criteria.add(Restrictions.eq("inventory_id",y));
	
		return (List<Inventory_Article>)criteria.list();
		
	}*/
	
	public Inventory_Article getInventory_ArticlebyId(String id){
		
		Inventory_Article inventory_Article = null;
		
		id = id == null ? "" : id;
		if(!id.equals(""))
			inventory_Article = getByKey(Long.parseLong(id));
		
		if(inventory_Article!=null)
			Hibernate.initialize(inventory_Article.getInvt_WareHouse_Master());
		
		return inventory_Article;
	}
	
	public void setInventory_Article(Inventory_Article invtArticle){
		persist(invtArticle);
	}
	
	public void deleteInventory_Article(Inventory_Article inventory_Article){
		delete(inventory_Article);
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory_Article> getInventory_ArticleListCriteria(String posId, String warehouseId, String inventory_id, String brand, String model,String artcle_id,String articlenum,String barcode,String status){
		
		posId = posId == null ? "" : posId;
		inventory_id = inventory_id == null ? "" : inventory_id;
	    brand = brand == null ? "" : brand;
	    warehouseId = warehouseId == null ? "" : warehouseId;
	    model = model == null ? "" : model;
	    artcle_id = artcle_id == null ? "" : artcle_id;
	    articlenum = articlenum == null ? "" : articlenum;
	    barcode = barcode == null ? "" : barcode;
	    status = status == null ? "" : status;
	    List<Inventory_Article> list = null;
	    try{
	    
		Criteria criteria = getSession().createCriteria(Inventory_Article.class).createAlias("invt_WareHouse_Master","WareHouseMaster").createAlias("invt_pos", "pos");
		
		if(!posId.equals("")) 
			criteria.add(Restrictions.eq("pos.pos_id",Long.parseLong(posId)));
		
		
		if(!brand.equals("")) 
			criteria.add(Restrictions.eq("device_brand",brand));
		
		if(!model.equals("")) 
			criteria.add(Restrictions.eq("device_model_no",model));
		
		if(!inventory_id.equals(""))
			criteria.add(Restrictions.eq("inventory_id",Long.parseLong(inventory_id)));
			
		if(!warehouseId.equals(""))
			criteria.add(Restrictions.eq("WareHouseMaster.warehouse_id",Long.parseLong(warehouseId)));
		
		if(!artcle_id.equals("")) 
			criteria.add(Restrictions.eq("article_id",artcle_id));
		
		if(!articlenum.equals("")) 
			criteria.add(Restrictions.eq("article_number",articlenum));
		
		if(!barcode.equals(""))
			criteria.add(Restrictions.eq("device_barcode",barcode));
			
		if(!status.equals(""))
			criteria.add(Restrictions.eq("status",status));
			list =  criteria.list();
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }
		
		
			return  list;
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Inventory_Article> getInvtArticleForCusomer(String posId, String itemType, String brand, String os, String ram, String status){
		
		posId = posId == null ? "" : posId;
	    brand = brand == null ? "" : brand;
	    itemType = itemType == null ? "" : itemType;
	    os = os == null ? "" : os;
	    ram = ram == null ? "" : ram;
	    status = status == null ? "" : status;
	    
		Criteria criteria = getSession().createCriteria(Inventory_Article.class).createAlias("invt_pos","PosMaster");
		
		if(!posId.equals("")) 
			criteria.add(Restrictions.eq("PosMaster.pos_id",Long.parseLong(posId)));
		
		if(!itemType.equals("")) 
			criteria.add(Restrictions.eq("inventory_id",Long.parseLong(itemType)));
		
		if(!brand.equals("")) 
			criteria.add(Restrictions.eq("device_brand",brand));
		
		if(!os.equals("")) 
			criteria.add(Restrictions.eq("device_os",os));
		
		if(!ram.equals("")) 
			criteria.add(Restrictions.eq("device_ram",ram));
		
		if(!status.equals("")) 
			criteria.add(Restrictions.eq("status",status));
		
		return (List<Inventory_Article>)criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DeviceList> getInvtArticleListForJsp(String posId, String brand, String os, String ram, String status){
		
		posId = posId == null ? "" : posId;
	    brand = brand == null ? "" : brand;
	    os = os == null ? "" : os;
	    ram = ram == null ? "" : ram;
	    status = status == null ? "" : status;
	    
	    List<DeviceList> list = new ArrayList<DeviceList>();
	    
	    logger.info(">>>>>>[getInvtArticleListForJsp]>>>>>>>>>>getting InvtArticleList>>>>>>>> with [posId,brand,os,ram,status]>>["+posId+","+brand+","+os+","+ram+","+status+"]>>>>>>>>>>>>");
	   
	    try{
		    Criteria criteria = getSession().createCriteria(Inventory_Article.class).createAlias("invt_pos","PosMaster");
		    if(!status.equals(""))
				criteria.add(Restrictions.eq("status",status));
	
			if(brand.equals("")) {
				
				if(!posId.equals(""))
					criteria.add(Restrictions.eq("PosMaster.pos_id",Long.parseLong(posId)));
				 
				criteria.setProjection(Projections.distinct(Projections.projectionList()
					.add(Projections.property("device_brand"), "list") ));
			}
		    
		    if(!brand.equalsIgnoreCase("")){ 
		    	
		    	if(!posId.equals(""))
		    		criteria.add(Restrictions.eq("PosMaster.pos_id",Long.parseLong(posId)));
		    	criteria.add(Restrictions.eq("device_brand",brand));
		    	
		    	criteria.setProjection(Projections.distinct(Projections.projectionList()
		    		    .add(Projections.property("device_os"), "list") ));
		    }
		    
		    if(!brand.equalsIgnoreCase("")&&!os.equals("")){ 
		    	
		    	if(!posId.equals(""))
		    		
		    		criteria.add(Restrictions.eq("PosMaster.pos_id",Long.parseLong(posId)));
			    	criteria.add(Restrictions.eq("device_brand",brand));
			    	criteria.add(Restrictions.eq("device_os",os));
			    	criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("device_ram"), "list") ));
			    	
		    }
		    
		    
		    list =  criteria.setResultTransformer(Transformers.aliasToBean(DeviceList.class)).list();
		    return list;
	    }catch(Exception ex){
	    	logger.error(">>>>>>[getInvtArticleListForJsp]>>>>>>>>>>getting InvtArticleList>>>>>>>> with [posId,brand,os,ram,status]>>["+posId+","+brand+","+os+","+ram+","+status+"]>>>>>>>>>>>>Failed!!!!!!!>>>>>> Due to >>>>>"+ex.getMessage());
	    	return null;
	    }
	  }
	
	@SuppressWarnings("unchecked")
	 public List<Inventory_Article> getDeviceList(String posId, String itemType, String esn, String status,String barcode){
	  
	  posId = posId == null ? "" : posId;
	  esn = esn == null ? "" : esn;
	     itemType = itemType == null ? "" : itemType;
	     status = status == null ? "" : status;
	     
	  Criteria criteria = getSession().createCriteria(Inventory_Article.class).createAlias("invt_pos","PosMaster");
	  
	  if(!posId.equals("")) 
	   criteria.add(Restrictions.eq("PosMaster.pos_id",Long.parseLong(posId)));
	  
	  if(!itemType.equals("")) 
	   criteria.add(Restrictions.eq("inventory_id",Long.parseLong(itemType)));
	  
	  if(!esn.equals("")) 
	   criteria.add(Restrictions.eq("article_number",esn));
	  
	  if(!status.equals("")) 
	   criteria.add(Restrictions.eq("status",status));
	  
	  if(!barcode.equals("")) 
		   criteria.add(Restrictions.eq("device_barcode",barcode));
	  
	  
	  return (List<Inventory_Article>)criteria.list();
	 }

	@Override
	public Invt_Esnnumber_Blacklist getInvt_Esnnumber_Blacklist(String esn) {
		Criteria criteria = getSession().createCriteria(Invt_Esnnumber_Blacklist.class);
		criteria.add(Restrictions.eq("esnnumber",esn));
		return  (Invt_Esnnumber_Blacklist) criteria.uniqueResult();
	}
	
	@Override
	public Inventory_Article getInventory_Article(String esn) {
		Criteria criteria = getSession().createCriteria(Inventory_Article.class);
		criteria.add(Restrictions.eq("article_number",esn));
		return  (Inventory_Article) criteria.uniqueResult();
	}
}
