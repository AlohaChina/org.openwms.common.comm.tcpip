/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.comm.handler;

import org.openwms.common.comm.CommonHeader;
import org.openwms.common.comm.err.ErrorCodes;
import org.openwms.common.comm.err.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * A CommonExceptionHandler.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@Component
public class CommonExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

    /**
     * Create a new CommonExceptionHandler.
     */
    public CommonExceptionHandler() {}

    /**
     * FIXME [scherrer] Comment this
     * 
     * @param telegram
     * @return
     */
    @ServiceActivator(inputChannel = "commonExceptionChannel", outputChannel = "outboundChannel")
    public ErrorMessage handle(String telegram) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Common error: " + telegram);
        }
        CommonHeader header = new CommonHeader(telegram);
        String sender = header.getSender();
        header.setSender(header.getReceiver());
        header.setReceiver(sender);
        return new ErrorMessage.Builder(header).withErrorCode(ErrorCodes.UNKNOWN_MESSAGE_TYPE).withCreateDate().build();
    }
}
