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
package org.openwms.common.comm.tcpip.transformer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.openwms.common.comm.tcpip.CommonMessage;
import org.openwms.common.comm.tcpip.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

/**
 * A CommonMessageTransformer.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@Component(value = "commonMessageTransformer")
public class CommonMessageTransformer {

    @Autowired
    private List<MessageMapper<CommonMessage>> mappers;
    private final Map<String, MessageMapper<CommonMessage>> mappersMap = new HashMap<String, MessageMapper<CommonMessage>>();

    /**
     * Create a new CommonMessageTransformer.
     */
    public CommonMessageTransformer() {}

    /**
     * Do this once to query a Map not a List.
     */
    @PostConstruct
    void onPostConstruct() {
        for (MessageMapper<CommonMessage> mapper : mappers) {
            mappersMap.put(mapper.forType(), mapper);
        }
    }

    /**
     * Transformer method to transform a telegram String <tt>telegram</tt> into
     * a {@link CommonMessage}.
     * 
     * @param telegram
     *            The incoming telegram String
     * @return The {@link CommonMessage} is transformable
     * @throws an
     *             RuntimeException if no appropriate type was found.
     */
    @Transformer
    public CommonMessage transform(String telegram) {
        MessageMapper<CommonMessage> mapper = mappersMap.get(CommonMessage.getTelegramType(telegram));
        if (mapper == null) {
            throw new RuntimeException("Not mapper found for telegram type " + CommonMessage.getTelegramType(telegram));
        }
        return mapper.mapTo(telegram);
    }
}
