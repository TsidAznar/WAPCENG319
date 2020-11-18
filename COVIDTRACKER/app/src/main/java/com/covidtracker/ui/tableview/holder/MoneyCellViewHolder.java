package com.covidtracker.ui.tableview.holder;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.covidtracker.R;
import com.covidtracker.ui.tableview.model.CellModel;

import org.fabiomsr.moneytextview.MoneyTextView;

import static com.covidtracker.R.id.money_cell_data;


public class MoneyCellViewHolder extends AbstractViewHolder {
    public final MoneyTextView cell_textview;
    public final LinearLayout cell_container;

    public MoneyCellViewHolder(View itemView) {
        super(itemView);
        cell_textview = itemView.findViewById(money_cell_data);
        cell_container = itemView.findViewById(R.id.cell_container);
    }

    public void setCellModel(CellModel p_jModel) {

        // set text
        cell_textview.setAmount(Float.parseFloat((String) p_jModel.getData()));

        // adjusting and measuring again
        cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        cell_textview.requestLayout();
    }

    @Override
    public void setSelected(SelectionState p_nSelectionState) {
        super.setSelected(p_nSelectionState);

        if (p_nSelectionState == SelectionState.SELECTED) {
            changeColorOfMoneyTextView(R.color.selected_text_color);
        } else {
            changeColorOfMoneyTextView(R.color.unselected_text_color);
        }
    }

    private void changeColorOfMoneyTextView(@ColorRes int id) {
        int color = ContextCompat.getColor(cell_textview.getContext(), id);

        cell_textview.setBaseColor(color);
        cell_textview.setDecimalsColor(color);
        cell_textview.setSymbolColor(color);
    }
}
