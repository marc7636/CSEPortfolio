package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.io.Serializable;

/**
 * @author Mads
 */
public record Event(Entity source) implements Serializable {
}
