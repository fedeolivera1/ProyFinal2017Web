package gpw.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gpw.dominio.persona.TipoDoc;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;

public class ServletObtTipoDoc extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			List<TipoDoc> listaTd = gpwStLoc.obtenerListaTipoDoc();
			final Gson gson = new Gson();
			final Type tipoListaDep = new TypeToken<List<TipoDoc>>(){}.getType();
			final String listaTdJson = gson.toJson(listaTd, tipoListaDep);
			System.out.print(listaTdJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(listaTdJson);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
