package com.caleumtatsu2010.techmate_session.cassandra.astra;

import com.caleumtatsu2010.cassandra.connection.astra.AstraConnector;
import com.caleumtatsu2010.cassandra.dao.account.CASAccountDao;
import com.caleumtatsu2010.cassandra.dao.cart.CASCartDao;
import com.caleumtatsu2010.cassandra.dao.cart.item.CASCartItemDao;
import com.caleumtatsu2010.cassandra.models.account.CASAccount;
import com.caleumtatsu2010.cassandra.models.cart.CASCart;
import com.caleumtatsu2010.cassandra.models.cart.item.CASCartItem;
import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;
import com.caleumtatsu2010.techmate_session.cassandra.astra.util.AstraCrud;

import java.util.List;

public class AstraHttpSessionSyncronizerCrud {
	
	private AstraConnector astraConnector;
	private String astraKeySpace;
	
	
	public void syncAstraCart(AstraSession astraSession) {
		CASCartDao casCartDao = new CASCartDao(this.astraConnector, this.astraKeySpace);
		CASCart casCart = astraSession.getCasCart();
		AstraCrud.syncAstraCrud(casCartDao, casCart, astraSession.getCasCart().getId());
	}
	
	public void syncAstraAccount(AstraSession astraSession) {
		CASAccountDao casAccountDao = new CASAccountDao(this.astraConnector, this.astraKeySpace);
		CASAccount casAccount = astraSession.getCasAccount();
		AstraCrud.syncAstraCrud(casAccountDao, casAccount, astraSession.getCasAccount().getId());
	}
	
	public void syncAstraCartItem(AstraSession astraSession) {
		CASCartItemDao casCartItemDao = new CASCartItemDao(this.astraConnector, this.astraKeySpace);
		List<CASCartItem> casCartItemList = astraSession.getCasCartItemList();
		for (int i = 0; i < casCartItemList.size(); i++) {
			AstraCrud.syncAstraCrud(casCartItemDao, casCartItemList.get(i), casCartItemList.get(i).getId());
		}
	}
	
	public void syncHttpSessionToAstra(AstraSession astraSession) {
		syncAstraAccount(astraSession);
		syncAstraCart(astraSession);
		syncAstraCartItem(astraSession);
	}
}
