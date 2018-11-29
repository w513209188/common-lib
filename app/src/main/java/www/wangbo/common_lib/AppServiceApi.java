package www.wangbo.common_lib;

import com.wb.baselib.bean.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppServiceApi {
    @GET("api/app/courseInfo/basis_id={basis_id}/st=1")
    Observable<Result<CourseInfoBean>> getLoginInfo(@Path("basis_id") String basis_id);
}
