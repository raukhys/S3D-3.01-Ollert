package mvc.vue.tache.contenu;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import javafx.scene.control.Button;
import mvc.vue.tache.VueTache;
import ollert.tache.TachePrincipale;

import java.time.LocalDate;

/**
 * VueCalendrier représente la vue du bouton de la date de fin d'une tâche
 */
public class VueCalendrier extends HBox implements Observateur {
    /**
     * Constructeur de la classe VueCalendrier
     * Charge l'icone du calendrier dans les ressources pour la façade du bouton
     */
    public VueCalendrier(){
        Image image = new Image("file:Sources/ressource/images/icones/calendrier.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.setBackground(null);
        this.getChildren().add(imageView);
    }

    /**
     * Actualise la vue courante
     * @param sujet le modèle à partir duquel la vue est actualisée
     */
    @Override
    public void actualiser(Sujet sujet) {
        ModeleOllert modele = (ModeleOllert) sujet;
        VueTache vueTache = (VueTache) this.getParent();
        TachePrincipale tache = (TachePrincipale) modele.getParent(vueTache.getLocalisation());
        LocalDate localDate = tache.getDateFin();
        if (localDate != null){
            this.getChildren().add(new Label(localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear()));
        }
    }
}
