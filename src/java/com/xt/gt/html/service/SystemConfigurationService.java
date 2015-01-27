

package com.xt.gt.html.service;

import com.xt.core.service.IService;
import com.xt.gt.sys.SystemConfiguration;
import java.util.Map;

/**
 *
 * @author albert
 */
public class SystemConfigurationService  implements IService {

    public SystemConfigurationService() {
    }

    public Map getParams () {
        return SystemConfiguration.getInstance().getParams();
    }

}
