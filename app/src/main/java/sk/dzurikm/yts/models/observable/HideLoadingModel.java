package sk.dzurikm.yts.models.observable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HideLoadingModel extends ViewModel {
    private final MutableLiveData<Boolean> selectedItem = new MutableLiveData<Boolean>();
    public void selectItem(Boolean hide) {
        selectedItem.setValue(hide);
    }
    public LiveData<Boolean> getSelectedItem() {
        return selectedItem;
    }
}
