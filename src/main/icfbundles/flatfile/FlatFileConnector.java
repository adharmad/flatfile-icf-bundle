package icfbundles.flatfile;

import org.identityconnectors.framework.api.operations.GetApiOp;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.common.objects.filter.FilterTranslator;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.PoolableConnector;
import org.identityconnectors.framework.spi.operations.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by adharmad on 11/22/2016.
 */
@ConnectorClass(configurationClass = FlatFileConfiguration.class, displayNameKey = "FlatFile")
public class FlatFileConnector implements SchemaOp,  CreateOp, DeleteOp,
        UpdateOp, SearchOp<Map<String, String>>, GetApiOp, PoolableConnector {

    private FlatFileConfiguration flatFileConfig;

    @Override
    public ConnectorObject getObject(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {
        return null;
    }

    @Override
    public void checkAlive() {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void init(Configuration configuration) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> set, OperationOptions operationOptions) {
        return null;
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions operationOptions) {

    }

    @Override
    public Schema schema() {
        return null;
    }

    @Override
    public FilterTranslator<Map<String, String>> createFilterTranslator(ObjectClass objectClass, OperationOptions operationOptions) {
        return null;
    }

    @Override
    public void executeQuery(ObjectClass objectClass, Map<String, String> stringStringMap, ResultsHandler resultsHandler, OperationOptions operationOptions) {

    }

    @Override
    public Uid update(ObjectClass objectClass, Uid uid, Set<Attribute> set, OperationOptions operationOptions) {
        return null;
    }
}
