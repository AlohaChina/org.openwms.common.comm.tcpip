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
package org.openwms.common.comm.exception;

/**
 * A MessageProcessingException is a general exception that indicates a fault
 * situation during message processing.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class MessageProcessingException extends RuntimeException {

    private static final long serialVersionUID = 4672945459737321505L;

    /**
     * Create a new MessageProcessingException.
     */
    public MessageProcessingException() {}

    /**
     * Create a new MessageProcessingException.
     * 
     * @param arg0
     */
    public MessageProcessingException(String arg0) {
        super(arg0);
    }

    /**
     * Create a new MessageProcessingException.
     * 
     * @param arg0
     */
    public MessageProcessingException(Throwable arg0) {
        super(arg0);
    }

    /**
     * Create a new MessageProcessingException.
     * 
     * @param arg0
     * @param arg1
     */
    public MessageProcessingException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
}