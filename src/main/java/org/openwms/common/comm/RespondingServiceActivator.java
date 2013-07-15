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
package org.openwms.common.comm;

import org.springframework.integration.MessageChannel;

/**
 * A RespondingServiceActivator.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public interface RespondingServiceActivator<T extends CommonMessage, U extends CommonMessage> {

    /**
     * The input-channel instance that is used by the processor to process
     * messages from.
     * 
     * @return The encapsulated MessageChannel instance.
     */
    MessageChannel getChannel();

    /**
     * Returns the unique name of the MessageChannel that is used as
     * input-channel for the processing messages.
     * 
     * @return Expected to be the unique name of the message concatenated with a
     *         suffix, defined in {@link CommConstants#CHANNEL_SUFFIX}
     */
    String getChannelName();

    /**
     * Wake up a service, processor or bean an that accepts incoming messages of
     * type <tt>T</tt> and returns messages of type <tt>U</tt>.
     * 
     * @param message
     *            The message to forward
     * @return The response returned from the service
     */
    U wakeUp(T message);
}
