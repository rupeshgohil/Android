/*
package rps.androidcontextualtoolbar;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

class ActionModeCallback implements ActionMode.Callback{
    public RecyclerViewAdapter Adapter;
    public ActionMode actionMode;
    public MainActivity com;
    public ActionModeCallback(RecyclerViewAdapter myAdapter,ActionMode mod,MainActivity activity) {
        this.Adapter = myAdapter;
        this.actionMode = mod;
        this.com = activity;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                deleterow();
                mode.finish();
                return true;
            case R.id.Color:
                updateColoredRows();
                mode.finish();
                return true;
            case R.id.All:
                com.SelectAll();
                mode.finish();
                return true;
            case R.id.Refresh:
                com.setDataAndAdapter();
                mode.finish();
                return true;
            default:
                return false;
        }
       // return false;
    }
    public void updateColoredRows(){
        List<Integer> selectedItemPositions =
                Adapter.getSelectedItem();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            Adapter.updateData(selectedItemPositions.get(i));
        }
        Adapter.notifyDataSetChanged();
        actionMode = null;
    }
    private void deleterow() {
        List<Integer> list = Adapter.getSelectedItem();
        for (int i = list.size() - 1; i >= 0; i--) {
            Adapter.removeData(list.get(i));
        }
        Adapter.notifyDataSetChanged();

        if (Adapter.getItemCount() == 0)
           // fab.setVisibility(View.VISIBLE);

        actionMode = null;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        Adapter.clearSelections();
        actionMode = null;
    }
}
*/
