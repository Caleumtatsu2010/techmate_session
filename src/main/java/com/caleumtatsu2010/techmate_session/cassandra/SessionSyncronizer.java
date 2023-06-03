package com.caleumtatsu2010.techmate_session.cassandra;

import com.caleumtatsu2010.techmate_session.cassandra.astra.model.AstraSession;

public interface SessionSyncronizer {
	public void syncSessionToCAS(AstraSession astraSession);
}
