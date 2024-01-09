package mvc.fabrique;

import mvc.modele.ModeleOllert;
import mvc.vue.liste.VueListeTableau;
import mvc.vue.page.VuePageTableau;
import mvc.vue.sousTache.VueSousTache;
import mvc.vue.sousTache.VueSousTacheTableau;
import mvc.vue.tache.VueTacheTableau;

/**
 * Implementation de la FabriqueVue pour un affichage en tableau
 * : affichage classique (colonnes)
 */
public class FabriqueVueTableau implements FabriqueVue {

	/**
	 * Crée la vue d'une tache sous forme de tableau
	 * @return Vue de la tache
	 */
	@Override
	public VueTacheTableau creerVueTache(ModeleOllert modeleControle) {
		return new VueTacheTableau(modeleControle);
	}

	/**
	 * Crée la vue d'une liste sous forme de tableau
	 *
	 * @param modeleControle
	 * @return Vue de la liste
	 */
	@Override
	public VueListeTableau creerVueListe(ModeleOllert modeleControle) {
		return new VueListeTableau(modeleControle);
	}

	/**
	 * Crée la vue d'une page sous forme de tableau
	 *
	 * @param modeleControle
	 * @return Vue de la page
	 */
	@Override
	public VuePageTableau creerVuePage(ModeleOllert modeleControle) {
		return new VuePageTableau(modeleControle);
	}

	@Override
	public VueSousTacheTableau creerVueSousTache(ModeleOllert modeleControle) {
		return new VueSousTacheTableau(modeleControle);
	}
}
