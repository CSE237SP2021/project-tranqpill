// move parser class

package tranqpill;

import java.util.*;

public class Move {

	private Square[]locations;
	
	public Move(Square[] locations) {
		this.locations=locations;
	}
	
	public Square[] getLocations() {
		return locations;
	}
}
