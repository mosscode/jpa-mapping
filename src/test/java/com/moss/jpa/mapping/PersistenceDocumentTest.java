/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of jpa-mapping.
 *
 * jpa-mapping is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * jpa-mapping is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jpa-mapping; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.jpa.mapping;

import javax.xml.bind.JAXBContext;

import junit.framework.TestCase;

import com.moss.jaxbhelper.JAXBHelper;

/**
<persistence xmlns="http://java.sun.com/xml/ns/persistence" version="1.0">
  <persistence-unit name="lender-data">
  	<provider>org.hibernate.ejb.HibernatePersistence</provider>
    <jta-data-source>lenderDataManaged</jta-data-source>
    <non-jta-data-source>lenderDataUnManaged</non-jta-data-source>
    <class>org.superbiz.injection.jpa.Movie</class>
	
    <properties>
      <property name="hibernate.hbm2ddl.auto" value="create-update"/>
      <property name="hibernate.transaction.manager_lookup_class"
                value="org.apache.openejb.hibernate.TransactionManagerLookup"/>
      <property name="hibernate.dialect" value="org.hibernate.dialect.DerbyDialect"/>
    </properties>
  </persistence-unit>
</persistence>
 */
public class PersistenceDocumentTest extends TestCase {
	
	public void testExecute() throws Exception {
		JAXBContext context = JAXBContext.newInstance(PersistenceDocument.class);
		JAXBHelper helper = new JAXBHelper(context);
		PersistenceDocument document = (PersistenceDocument) context.createUnmarshaller()
										.unmarshal(this.getClass().getResourceAsStream("persistence.xml"));
		String data = helper.writeToXmlString(document);
		System.out.println(data);
		{
			PersistenceDocument doc = new PersistenceDocument();
			PersistenceUnitConfig unit = new PersistenceUnitConfig("lender-data");
			unit.setProviderClass("org.hibernate.ejb.HibernatePersistence");
			unit.setJtaDataSourceName("lenderDataManaged");
			unit.setNonJtaDataSourceName("lenderDataUnManaged");
			unit.addClass("org.superbiz.injection.jpa.Movie");
			unit.setProperty("hibernate.hbm2ddl.auto", "create-update");
			unit.setProperty("hibernate.transaction.manager_lookup_class", "org.apache.openejb.hibernate.TransactionManagerLookup");
			unit.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
			doc.add(unit);
			System.out.println(helper.writeToXmlString(doc));
		}
	}
}
