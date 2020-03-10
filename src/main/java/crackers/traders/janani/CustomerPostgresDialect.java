package crackers.traders.janani;

import org.hibernate.dialect.function.VarArgsSQLFunction;

public class CustomerPostgresDialect  extends
	org.hibernate.dialect.PostgreSQL82Dialect {
	    CustomerPostgresDialect() {
	      super();
	      registerFunction("ilike", new
	VarArgsSQLFunction(null, ""," ilike ", ""));
	   }
}

