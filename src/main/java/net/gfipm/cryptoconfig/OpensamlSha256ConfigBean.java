/*
 * Copyright 2013 Georgia Tech Research Institute
 * Copyright 2007 University Corporation for Advanced Internet Development, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.gfipm.cryptoconfig;

import org.opensaml.xml.Configuration;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.security.BasicSecurityConfiguration;
import org.opensaml.xml.security.SecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import edu.internet2.middleware.shibboleth.common.config.OpensamlConfigBean;

/**
 * A simple initializing bean that may be used with Spring to do some custom post-initialization 
 * configuration of crypto settings for the OpenSAML library. Must be called after OpenSAML is initialized,
 * for example via {@link OpensamlConfigBean}.
 */
public class OpensamlSha256ConfigBean implements InitializingBean {
    
    /** Class logger. */
    private final Logger log = LoggerFactory.getLogger(OpensamlSha256ConfigBean.class);

    /** {@inheritDoc} */
    public void afterPropertiesSet() throws Exception {
        
        SecurityConfiguration config = Configuration.getGlobalSecurityConfiguration();
        if (config instanceof BasicSecurityConfiguration) {
            BasicSecurityConfiguration secConfig =
                (BasicSecurityConfiguration) Configuration.getGlobalSecurityConfiguration();

            log.info("Overriding global OpenSAML digital signature algoriithm URI (RSA with SHA1) to: {}",
                    SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);

             secConfig.registerSignatureAlgorithmURI("RSA", SignatureConstants.ALGO_ID_SIGNATURE_RSA_SHA256);

            log.info("Overriding global OpenSAML digest method algorithm URI (SHA256) to: {}",
                    SignatureConstants.ALGO_ID_DIGEST_SHA256);
             secConfig.setSignatureReferenceDigestMethod (SignatureConstants.ALGO_ID_DIGEST_SHA256);
            
        } else {
        	log.warn("Configuration.getGlobalSecurityConfiguration did not produce an instance of BasicSecurityConfiguration, " 
        			+ "could not override crypto settings: {}", config == null ? "null" : config.getClass().getName());
        }
    }
}
