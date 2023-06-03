package com.caleumtatsu2010.techmate_session.cassandra;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.dao.account.CASAccountDao;
import com.caleumtatsu2010.cassandra.dao.cart.CASCartDao;
import com.caleumtatsu2010.cassandra.dao.cart.item.CASCartItemDao;
import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.cassandra.models.database.astra.KeySpace;
import com.caleumtatsu2010.techmate_session.cassandra.controllers.CASSessionAdapter;
import com.caleumtatsu2010.techmate_session.cassandra.model.CASSession;

import java.util.List;
import java.util.UUID;

public class AstraSessionSyncronizer {
	
	private CASSession casSession;
	private CASCartDao casCartDao;
	private CASAccountDao casAccountDao;
	private CASCartItem casCartItem;
	
	public AstraSessionSyncronizer() {
	
	}
	
	public void sync(AstraConnector astraConnector, CASSession casSession) {
		CASAccountDao CASAccountDao = new CASAccountDao(astraConnector, KeySpace.techmate);
		CASAccount casAccount = casSession.getCasAccount();
		if (casAccountDao.get(casAccount.getId()) != null) {
			CASAccountDao.update(casAccount, casAccount.getId());
		} else {
			CASAccountDao.insert(casAccount);
		}
		
		CASCartDao casCartDao = new CASCartDao(astraConnector, KeySpace.techmate);
		CASCart casCart = casSession.getCasCart();
		if (casCartDao.get(casCart.getId()) != null) {
			casCartDao.update(casCart, casCart.getId());
		} else {
			casCartDao.insert(casCart);
		}
		
		CASCartItemDao casCartItemDao = new CASCartItemDao(astraConnector, KeySpace.techmate);
		List<CASCartItem> casCartItemList = casSession.getCasCartItemList();
		for (int i = 0; i < casCartItemList.size(); i++) {
			if (casCartItemDao.get(casCartItemList.get(i).getId()) != null) {
				casCartItemDao.update(casCartItemList.get(i), casCartItemList.get(i).getId());
			} else {
				casCartItemDao.insert(casCartItemList.get(i));
			}
		}
	}
	
	
}
