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
package org.openwms.common.comm.err.tcp;

import java.text.ParseException;

import org.openwms.common.comm.CommConstants;
import org.openwms.common.comm.CommonHeader;
import org.openwms.common.comm.CommonMessage;
import org.openwms.common.comm.MessageMapper;
import org.openwms.common.comm.err.ErrorMessage;
import org.openwms.common.comm.exception.MessageMissmatchException;
import org.openwms.common.comm.util.CommonMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * An ErrorTelegramMapper transforms an incoming OSIP telegram string, that is
 * expected to be an error telegram, into an ErrorMessage.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@Component
public class ErrorTelegramMapper implements MessageMapper<ErrorMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorTelegramMapper.class);

    /**
     * @see org.openwms.common.comm.MessageMapper#mapTo(java.lang.String)
     */
    @Override
    public ErrorMessage mapTo(String telegram) {
        int startPayload = CommonHeader.getHeaderLength() + forType().length();
        int startCreateDate = startPayload + CommonMessage.getErrorCodeLength();
        try {
            return new ErrorMessage.Builder(CommonMessageFactory.createHeader(telegram))
                    .withErrorCode(telegram.substring(startPayload, startCreateDate))
                    .withCreateDate(
                            CommConstants.asDate(telegram.substring(startCreateDate,
                                    startCreateDate + CommonMessage.getDateLength()))).build();
        } catch (ParseException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error while parsing telegram:" + e.getMessage());
            }
            throw new MessageMissmatchException(e.getMessage());
        }
    }

    /**
     * @see org.openwms.common.comm.MessageMapper#forType()
     */
    @Override
    public String forType() {
        return ErrorMessage.IDENTIFIER;
    }
}
