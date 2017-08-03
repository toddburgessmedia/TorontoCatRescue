package com.toddburgessmedia.torontocatrescue.view;

import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public interface PetDetailView {

    void startProgressDialog();
    void stopProgressDialog();
    void updateView(PetDetailInfo info);
    void addBondedCardView(LimitedPetDetail limitedBonded, String bondedFriend);
    void createErrorToast();
    void updateShareActionProvider(PetDetail petDetail);
    void getMoreInfoEmail(PetDetail petDetail, LimitedPetDetail bondedFriend);
}
