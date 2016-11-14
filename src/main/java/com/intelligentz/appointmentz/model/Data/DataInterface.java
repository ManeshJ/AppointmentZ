package com.intelligentz.appointmentz.model.Data;

import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.ApplicationInformation;

/**
 * Created by Malinda on 10/19/2015.
 *
 * Impliment this interface depend on database connection
 */
public interface DataInterface {
    public ApplicationInformation getAppInfo(String applicationId);
    public ApplicationInformation loadPropFile() throws IdeabizException;
    boolean saveTokenFile(ApplicationInformation applicationInformation);
}
