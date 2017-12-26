package br.com.saodimas.view.components.combo;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.saodimas.model.beans.PlanoVO;

@SuppressWarnings("serial")
public class PlanoComboBoxModel extends AbstractListModel implements ComboBoxModel{

	private PlanoVO selectedPlano;
	private List<PlanoVO> arrayProduto;
	
	public PlanoComboBoxModel(List<PlanoVO> anLista) {
		this.arrayProduto = anLista;
	}
	
	public Object getSelectedItem() {
		return selectedPlano;
	}

	public void setSelectedItem(Object anItem) {
		this.selectedPlano = (PlanoVO)anItem;
	}

	public PlanoVO getElementAt(int index) {
		return arrayProduto.get(index);
	}

	public int getSize() {
		return arrayProduto.size();
	}


}
