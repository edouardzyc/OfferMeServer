package com.offerme.server.database.dao;

import com.offerme.server.database.model.Offer;

public interface OfferDao {
	
	public int insertOffer(Offer offer) throws Exception;
    
    public void updateOffer(Offer offer) throws Exception;
    
    public void deleteOffer(int offerId) throws Exception;
    
    public Offer getOffer(int offerId) throws Exception;

}
