package es.upm.dit.isst.electolab.model;

public enum Comunidades {
	ANDALUCÍA, ARAGÓN, ASTURIAS, BALEARES, CANARIAS, CANTABRIA, CASTILLALAMANCHA, CASTILLAYLEÓN, CATALUÑA, CEUTA, EXTREMADURA, GALICIA, LARIOJA, MADRID, MELILLA, MURCIA, NAVARRA, PAÍSVASCO, VALENCIA;

	@Override
	public String toString() {
		switch (this) {
		case CASTILLALAMANCHA:
			return "Castilla La Mancha";
		case CASTILLAYLEÓN:
			return "Castilla y León";
		case PAÍSVASCO:
			return "País Vasco";
		case MADRID:
			return "Comunidad de Madrid";
		default:
			char c[] = super.toString().toLowerCase().toCharArray();
			c[0] = Character.toUpperCase(c[0]);
			return new String(c);
		}
	}
}
