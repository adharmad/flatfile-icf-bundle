package icfbundles.flatfile;

import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.io.File;
import java.io.IOException;

/**
 * Created by adharmad on 11/22/2016.
 */
public class FlatFileConfiguration extends AbstractConfiguration {

    /*
     * Name of the flat file which this connector bundle will read for data
     */
    private String flatFileName;

    /*
     * Field delimiter in the flat file
     */
    private String fieldDelimiter = ",";

    /*
     * Name of the UID attribute in the flat file
     */
    private String uidAttributeName;

    /*
     * Name of the NAME attribute in the flat file
     */
    private String nameAttributeName;

    /*
     * Name of the changelog attribute in the flat file
     */
    private String changeLogAttributeName;

    public String getFlatFileName() {
        return flatFileName;
    }

    @ConfigurationProperty(order = 1, helpMessageKey = "USER_ACCOUNT_FLATFILE_NAME_HELP",
            displayMessageKey = "USER_ACCOUNT_FLATFILE_NAME_DISPLAY", required=true)
    public void setFlatFileName(String flatFileName) {
        this.flatFileName = flatFileName;
    }

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    @ConfigurationProperty(order = 2, helpMessageKey = "USER_ACCOUNT_FIELD_DELIMITER_HELP",
            displayMessageKey = "USER_ACCOUNT_FIELD_DELIMITER_DISPLAY")
    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public String getUidAttributeName() {
        return uidAttributeName;
    }

    @ConfigurationProperty(order = 2, helpMessageKey = "USER_ACCOUNT_UID_ATTRIBUTE_NAME_HELP",
            displayMessageKey = "USER_ACCOUNT_UID_ATTRIBUTE_NAME_DISPLAY", required=true)
    public void setUidAttributeName(String uidAttributeName) {
        this.uidAttributeName = uidAttributeName;
    }

    public String getNameAttributeName() {
        return nameAttributeName;
    }

    @ConfigurationProperty(order = 2, helpMessageKey = "USER_ACCOUNT_NAME_ATTRIBUTE_NAME_HELP",
            displayMessageKey = "USER_ACCOUNT_NAME_ATTRIBUTE_NAME_DISPLAY", required=true)
    public void setNameAttributeName(String nameAttributeName) {
        this.nameAttributeName = nameAttributeName;
    }

    public String getChangeLogAttributeName() {
        return changeLogAttributeName;
    }

    @ConfigurationProperty(order = 2, helpMessageKey = "USER_ACCOUNT_CHANGELOG_ATTRIBUTE_NAME_HELP",
            displayMessageKey = "USER_ACCOUNT_CHANGELOG_ATTRIBUTE_NAME_DISPLAY")
    public void setChangeLogAttributeName(String changeLogAttributeName) {
        this.changeLogAttributeName = changeLogAttributeName;
    }

    @Override
    public void validate() {
        File flatFile = new File(flatFileName);

        if (!flatFile.isFile()) {
            throw new ConfigurationException("User store " + flatFileName + " is not a file " );
        }

        if (!flatFile.canRead()) {
            throw new ConfigurationException("Cannot read User store file " + flatFileName);
        }



    }
}
