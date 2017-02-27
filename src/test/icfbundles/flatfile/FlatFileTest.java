package icfbundles.flatfile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.api.ConnectorInfoManager;
import org.identityconnectors.framework.api.ConnectorInfoManagerFactory;
import org.identityconnectors.framework.api.ConnectorKey;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.Uid;

public class FlatFileTest {
    
	public static void main(String args[]) {
		ConnectorFacade ffConnectorFacade;

		String bundleName = "icfbundles.flatfile";
		String bundleVersion = "1.0";

	    String connectorClassName = "icfbundles.flatfile.FlatFileConnector";
	    File connectorJarFile = new File("C:/Users/adharmad/Dropbox/dev/github/flatfile-icf-bundle/dist/icfbundles.flatfile-1.0.jar");
	    URL bundleURL = null;
	    try {
            bundleURL = connectorJarFile.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
	    
	    System.out.println("URL of the bundle being tested: " + bundleURL);
	    
		// Get connector info manager
		ConnectorInfoManagerFactory cInfoManagerFactory = ConnectorInfoManagerFactory.getInstance();
		ConnectorInfoManager cInfoManager = cInfoManagerFactory.getLocalManager(bundleURL);
		System.out.println("Connector Info Manager: " + cInfoManager);

		// Search if connector is present
		ConnectorKey ffConnectorKey = new ConnectorKey(bundleName, bundleVersion, connectorClassName);
		System.out.println("Connector Key being searched: " + ffConnectorKey);
		
		ConnectorInfo cInfo = cInfoManager.findConnectorInfo(ffConnectorKey);
		System.out.println("Connector Info received: " + cInfo);

		// From the ConnectorInfo object, create the default APIConfiguration.
		APIConfiguration apiConfig = cInfo.createDefaultAPIConfiguration();
		ffConnectorFacade = ConnectorFacadeFactory.getInstance().newInstance(apiConfig);
		
		System.out.println("Validating configuration");
		try {
		    ffConnectorFacade.validate();
		    System.out.println("Validation successful");
		} catch (Exception e) {
		    System.out.println("Validation failed : " + e);
		}
		
		System.out.println("Connector supported ops: " + ffConnectorFacade.getSupportedOperations());
		
		System.out.println("Testing connector");
		try {
		    ffConnectorFacade.test();
		    System.out.println("Testing successful");
		} catch (UnsupportedOperationException e) {
		    System.out.println("Testing failed!");
		}

		
		System.out.println("Testing create op");
		try {
			AttributeBuilder attrBuilder = new AttributeBuilder();
			Attribute attr1 = attrBuilder.build("__NAME__", UUID.randomUUID().toString());
			
			//AttributeBuilder attrBuilder1 = new AttributeBuilder();
			Attribute attr2 = attrBuilder.build("ABCD", "abcd");
			
			Set<Attribute> attrs = new HashSet<>();
			attrs.add(attr1);
			attrs.add(attr2);
			
		    Uid uid = ffConnectorFacade.create(ObjectClass.ACCOUNT, attrs, null);
		    System.out.println("Create successful uid = " + uid);
		    
		} catch (Exception e) {
		    System.out.println("create failed : " + e);
		}
		
		System.out.println("Testing update op");
		try {
			AttributeBuilder attrBuilder = new AttributeBuilder();
			Attribute attr1 = attrBuilder.buildEnabled(true);
			
			Set<Attribute> attrs = new HashSet<>();
			attrs.add(attr1);
			
		    Uid uid = ffConnectorFacade.update(ObjectClass.ACCOUNT, new Uid("hello_world"), attrs, null);
		    System.out.println("Update successful uid = " + uid);
		    
		} catch (Exception e) {
		    System.out.println("Update failed : " + e);
		}

		
		System.out.println("Testing delete op");
		try {
		    ffConnectorFacade.delete(ObjectClass.ACCOUNT, new Uid("hello_world"), null);;
		    System.out.println("Delete successful");
		} catch (UnsupportedOperationException e) {
		    System.out.println("Delete failed!");
		}
	}

}
