package debug;


import com.ulang.comlib.BaseApplication;
import com.ulang.modelmvp.IMModelImlp;

public class MvpApplication extends BaseApplication {

    @Override
    public void initApp() {


        IMModelImlp.init(this);

    }
}
