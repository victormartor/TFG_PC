/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Modelos;

import Data.Clases.Articulo;
import Data.Clases.Categoria;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author victor
 */
public class ModCategorias extends AbstractListModel
{
    private ArrayList<Categoria> lCategoria;
    
    public ModCategorias(int iId_Marca) throws Exception
    {
        lCategoria = Categoria.Select(null, null, iId_Marca);
    }

    @Override
    public int getSize() {
        return lCategoria.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lCategoria.get(index);
    }
    
    public void addCategoria(Categoria categoria) throws Exception
    {
        lCategoria.add(categoria);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public void removeCategoria(int index) throws Exception
    {
        Categoria categoria = lCategoria.get(index);
        
        ArrayList<Articulo> aArticulos = Articulo.Select(null, null, categoria.getId(), null);
        for(Articulo a : aArticulos){
            a.Delete();
        }
        categoria.Delete();
        
        
        lCategoria.remove(index);
        this.fireIntervalRemoved(index, getSize(), getSize()+1);
    }
    
    public Categoria getCategoria(int index)
    {
        return lCategoria.get(index);
    }
}
