package servlets.utilitaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;
import dao.CategorieDao;
import dao.DAOException;
import dao.DonateurDAO;
import dao.ChevalDAO;
import dao.TagDAO;
import entities.Admin;
import entities.Categorie;
import entities.Donateur;
import entities.Cheval;
import entities.Tag;
import entities.Tranche;

/**
 * Servlet implementation class JeuxDeDonnees
 */
@WebServlet( "/Jeux" )
public class JeuxDeDonnees extends HttpServlet {
    private static final long   serialVersionUID = 1L;

    private static final String PAGE_CONNEXION   = "/WEB-INF/pageConnexion.jsp";

    @EJB
    private ChevalDAO           chevalDao;

    @EJB
    private CategorieDao        categorieDao;

    @EJB
    private DonateurDAO        financeurPorteurDao;

    @EJB
    private AdminDAO            adminDao;

    @EJB
    private TagDAO              tagDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JeuxDeDonnees() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        insertDb();
        request.getRequestDispatcher( PAGE_CONNEXION ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
    }

    private void insertDb() {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.MONTH, 2 );
        Calendar fini = Calendar.getInstance();
        fini.add(Calendar.MONTH, -1);
        for ( int i = 1; i <= 11; i++ ) {
            Cheval cheval = new Cheval();
            Donateur financeurPorteur = new Donateur();
            Admin admin = new Admin();
            Categorie categorie = new Categorie();
            Tag tag = new Tag();
            Tag tag2 = new Tag();

            financeurPorteur.setLogin( "test" + i );
            financeurPorteur.setNom( "test" + i );
            financeurPorteur.setMdp( "test" + i );
            financeurPorteur.setMontantAInvestir( i * 100 );
            financeurPorteur.setEmail( "test" + i + "@domain.com" );

            admin.setLogin( "admin" + i );
            admin.setNom( "admin" + i );
            admin.setMdp( "admin" + i );
            admin.setEmail( "admin" + i + "@domain.com" );

            categorie.setTitreCategorie( "categorie " + i );

            cheval.setNomCheval( "titreCheval " + i );
            cheval.setButArgent( "butArgent " + i );
            cheval.setDescription( "description " + i );
            cheval.setDateCreation( Calendar.getInstance() );
            cheval.setMontantDemande( i * 1000 );

            List<Tranche> trancheList = new ArrayList<Tranche>();
            Tranche trancheDepartTranche = new Tranche( "aucune compensation", 0 );
            trancheDepartTranche.setCheval( cheval );
            trancheList.add( trancheDepartTranche );
            for ( int j = 1; j <= 5; j++ ) {
                Tranche tranche = new Tranche();
                tranche.setCompensation( "compensation " + ( j ) );
                tranche.setMontantTranche( j * 10 );
                trancheList.add( tranche );
                tranche.setCheval( cheval );
            }
            cheval.setTrancheList( trancheList );

            tag.setTag( "tag" + i );
            tag.getChevauxList().add( cheval );
            tag2.setTag( "tagliatelle" + i );
            tag2.getChevauxList().add( cheval );

            if ( i % 3 == 0 ) {
                cheval.setEnAvant( true );
            }

            try {
                financeurPorteurDao.create( financeurPorteur );
                adminDao.create( admin );
                categorieDao.create( categorie );
                cheval.setCategorie( categorie );
                chevalDao.create( cheval );
                tagDao.create( tag );
                tagDao.create( tag2 );
            } catch ( DAOException e ) {
                e.printStackTrace();
            }
        }
    }

}
