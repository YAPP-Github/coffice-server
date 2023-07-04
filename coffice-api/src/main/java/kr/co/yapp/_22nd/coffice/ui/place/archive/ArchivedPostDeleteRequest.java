package kr.co.yapp._22nd.coffice.ui.place.archive;

import lombok.Data;

import java.util.List;

@Data
public class ArchivedPostDeleteRequest {
    private List<Long> postIds;
}
