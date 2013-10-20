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

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**

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
 */
@XmlType(name="persistence-unit", namespace="http://java.sun.com/xml/ns/persistence", propOrder={
	  	"providerClass", 
	  	"jtaDataSourceName",
	  	"nonJtaDataSourceName",
	  	"persistentClasses",
	  	"excludeUnlistedClasses",
	  	"properties"
		
})
public class PersistenceUnitConfig {
	private String name;
	private String providerClass;
	private String jtaDataSourceName;
	private String nonJtaDataSourceName;
	
	private Boolean excludeUnlistedClasses = true;
	
	private List<String> persistentClasses = new LinkedList<String>();
	private List<PersistenceUnitProperty> properties = new LinkedList<PersistenceUnitProperty>();
	
	public PersistenceUnitConfig() {
	}
	
	public PersistenceUnitConfig(String name) {
		super();
		this.name = name;
	}

	public void setProperty(String name, String value){
		this.properties.add(new PersistenceUnitProperty(name, value));
	}
	
	public void add(PersistenceUnitProperty property){
		this.properties.add(property);
	}
	
	public void add(Class persistentClass){
		this.persistentClasses.add(persistentClass.getCanonicalName());
	}
	
	public void addClass(String className){
		this.persistentClasses.add(className);
	}
	
	@XmlAttribute
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="provider")
	public final String getProviderClass() {
		return providerClass;
	}
	public final void setProviderClass(String providerClass) {
		this.providerClass = providerClass;
	}
	@XmlElement(name="jta-data-source")
	public final String getJtaDataSourceName() {
		return jtaDataSourceName;
	}
	public final void setJtaDataSourceName(String jtaDataSourceName) {
		this.jtaDataSourceName = jtaDataSourceName;
	}
	@XmlElement(name="non-jta-data-source")
	public final String getNonJtaDataSourceName() {
		return nonJtaDataSourceName;
	}
	public final void setNonJtaDataSourceName(String nonJtaDataSourceName) {
		this.nonJtaDataSourceName = nonJtaDataSourceName;
	}
	@XmlElement(name="class")
	public final List<String> getPersistentClasses() {
		return persistentClasses;
	}
	public final void setPersistentClasses(List<String> persistentClasses) {
		this.persistentClasses = persistentClasses;
	}
	@XmlElementWrapper(name="properties")
	@XmlElement(name="property")
	public final List<PersistenceUnitProperty> getProperties() {
		return properties;
	}
	public final void setProperties(List<PersistenceUnitProperty> properties) {
		this.properties = properties;
	}

	@XmlElement(name="exclude-unlisted-classes")
	public final Boolean getExcludeUnlistedClasses() {
		return excludeUnlistedClasses;
	}

	public final void setExcludeUnlistedClasses(Boolean excludeUnlistedClasses) {
		this.excludeUnlistedClasses = excludeUnlistedClasses;
	}
	
}
