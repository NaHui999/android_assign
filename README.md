# android_assign

:fire:첫번째 세미나(10/10)
==============================

-:blue_heart:  ​필수과제(완료:10/17)

> 1.MainActivity
> 로그인 화면의 모습이다.
> 회원가입 버튼을 누르면 2.secondActivity로 넘어간다. 넘어갈때는 intent를 사용한다.


![MainActivity](https://user-images.githubusercontent.com/53042824/96276090-852efb00-100d-11eb-9595-e38f010f69b7.png)

> 2.SecondActivity
> 회원 가입 화면으로 SignUpActivity이다.
> EditTextView에서 inputType을 변화시키면 password부분의 글자가 안보이게 할 수 있다.
> hint속성을 적용하면 입력이 없는 상태에서 미리보기 글씨가 나타난다.

#기본화면의 모습


![SecondActivity](https://user-images.githubusercontent.com/53042824/96276205-a1cb3300-100d-11eb-94bf-510b5641ff75.png)

하나라도 입력이 안되었을 때
Toast메세지로 입력되지 않은 값이 있음을 알려준다.

> #실패 토스트메세지

![second_fail](https://user-images.githubusercontent.com/53042824/96276273-bad3e400-100d-11eb-9d41-1afeaee11977.png)

모든 input이 들어온다음 버튼을 눌렀을 때
회원가입 성공 toast메세지를 출력한다.

> #성공 토스트 메세지

![second_success](https://user-images.githubusercontent.com/53042824/96276336-cd4e1d80-100d-11eb-8d70-17039260fec2.png)

(과제완료 10/17)- 보완 필요/readme에 코드블럭 넣는법 배우기->확인



-:tiger: : ​심화과제 1 (과제완료 10/29)
회원가입 이후 main화면으로 돌아가면 id와 password가 입력된 상태이다.
startActivityForResult을 활용하였고, onActivityResult를 오버라이드하여 내부 설정을 바꿨다.
id와 pw를 이전 화면에서 데이터 값을 받아와서 setText를 한다.

-사진 사이즈 줄이는 방법을 다시 찾아봐야함-> <img src="" width=""로 가능

<img src="https://user-images.githubusercontent.com/53042824/97703533-20d36780-1af4-11eb-826d-a9995134da4f.png" width="40%">



-:tiger: 심화과제 2 ( 미완: )
sharedPreferences를 사용해 자동로그인을 구현한다.
자동로그인 버튼을 따로 추가하였다. 




:fire:두번째 세미나(10/10)
===============================

-:blue_heart: 필수과제 (과제완료:10/30)

:sweat_drops: 에러상황 >> ​ 두번째 세미나 필수 과제 단독으로는 실행이 되는데, 1주차 과제와 함께 붙이면 (전체 합본에서) RecyclerView에서 Recycler_detail로 넘어가는 과정에서 중간에 멈추는 상황이 발생한다. 
어떤 요인이 문제인지 확인이 필요함.

> 두번째 필수 과제 단독 실행은 https://github.com/NaHui999/2nd_Seminar 에 올려두었음. 
>
> > 3주차 과제를 하며 Recylcer fragment를 만들때 appbar를 따로 만들 수 없어, 프래그먼트엔 linear만 배치하였음. grid 와 linear 오가는 심화과제 1또한  위 링크에서 확인 가능함.
> >
> > 

item_list_recycler.xml을 둬서 계속 반복 사용될 아이템 레이아웃을 둔다.
그리고 각 아이템에 들어가는 데이터를 RecyclerData로 두었으며
데이터를 뷰에 넣어주는 역할이 RecyclerViewHolder이고,
아이템 별 뷰 홀더를 생성하고 데이터를 엮는 역할은 RecyclerAdapter이다.

나의 프로필을 Recycler뷰로 만든다음 Adapter에 intent를 추가하여 데이터를 담아 
보내서 Recycler_detail 창을 만들어 자세한 내용을 표시해준다.

```
class MainActivity : AppCompatActivity() {
    private lateinit var RecyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RecyclerAdapter = RecyclerAdapter(this)

        main_rcv.adapter = RecyclerAdapter
        main_rcv.layoutManager = LinearLayoutManager(this) //이 레이아웃을 격자형으로든 바꿀 수 있다.

        RecyclerAdapter.data = mutableListOf(
                RecyclerData("이름","나희정","2020/10/1","이름에 대한 자세한 설명"),
                RecyclerData("나이","22","2020/10/2","나이에 대한 자세한 설명"),
                RecyclerData("파트","안드로이드","2020/10/3","파트에 대한 자세한 설명"),
                RecyclerData("GitHub","www.github.com/nahui999","2020/10/4","깃헙에 대한 자세한 설명"),
                RecyclerData("Blog","None","2020/10/5","블로그에 대한 자세한 설명"),
                RecyclerData("Sopt","www.sopt.org","2020/10/6","솝트에 대한 자세한 설명")
        )
        RecyclerAdapter.notifyDataSetChanged()

    }
}
```

위는 recycler 창의 모습. Adapter를 불러온다.

```
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
    
        val view=LayoutInflater.from(context).inflate(R.layout.item_list_recycler,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size 
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener{

            val intent=Intent(holder.itemView.context,Activity_Recycler_Detail::class.java)

            intent.putExtra("title",data[position].title)
            intent.putExtra("subtitle",data[position].subTitle)
            intent.putExtra("date",data[position].date)
            intent.putExtra("add",data[position].add)

            startActivity(holder.itemView.context,intent,null)
        }
```

RecyclerAdapter는 onCreateViewHolder와 getItemCount, onBindViewHolder를 필수적으로 오버라이드 해야한다.

```
class RecyclerViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){//val,var하면 선언과 동시에 초기화.
private val title:TextView=itemView.findViewById(R.id.item_title) //바인딩시킴.
    private val subTitle:TextView=itemView.findViewById(R.id.item_subtitle)

    fun onBind(data: RecyclerData){//Recyclerdata.kt가 객체로 들어오게됨.
        title.text=data.title
        subTitle.text=data.subTitle
    }
}
```

recyclerViewHolder는 데이터를 바인딩을 시키는 역할을 한다.

<img src="https://user-images.githubusercontent.com/53042824/97704325-8bd16e00-1af5-11eb-90ee-58d46edcb9b9.png" width="40%">

<img src="https://user-images.githubusercontent.com/53042824/97704344-95f36c80-1af5-11eb-8a33-5364e4e3b66d.png" width="40%">


 -:tiger: : ​심화과제1 ( 완성: 11/1)

 grid와 linear layout을 왔다갔다 할수 있도록 appbar을 추가하여 진행하였음.

> 3주차 과제를 하며 appbar를 fragment에 추가하는 것에 어려움을 겪어 따로 분리하여 repository를 생성함.

> 링크: https://github.com/NaHui999/2nd_Seminar 

<img src="https://user-images.githubusercontent.com/53042824/98339258-c59ef900-204e-11eb-82cd-d620138c7eb6.png" width="40%">

<img src="https://user-images.githubusercontent.com/53042824/98339267-c9328000-204e-11eb-9847-7beb1296d3f5.png" width="40%">




 -:tiger:  :심화과제2 (미완)





:fire:세번째 세미나(10/10)
===============================

-:blue_heart: 필수과제 (완성: 11/5)

Activity 위에 여러개의 fragment를 띄워 개별 작동하게 만들 수 있음. 

-> 중복 피하기위한 방법

프래그먼트엔 onCreate(), onCreateView(), on ActivityCreated()가 있다.

여기서는 onCreateView()만 사용한다.



<img width="631" alt="1" src="https://user-images.githubusercontent.com/53042824/98340355-94bfc380-2050-11eb-9b2c-1bebce1a3286.png">
<img width="960" alt="2" src="https://user-images.githubusercontent.com/53042824/98340357-95f0f080-2050-11eb-860e-4b26702c6d2e.png">

동영상이 추가가 안되어서 우선 스크린샷으로 대체

<video src="C:\Users\hui99\Downloads\20201106_162238.mp4"></video>

"""

```
class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        return view
    }
}
```

"""

이 과제에서는 Activity1개와, Profile/Recycler/Empty Fragment와

ProfileFragment 내의 Info와 Other Fragment로 이루어진다.



Viewpager를 이용해 슬라이드 형식으로 보여줄수 있는데, ViewPagerAdapter를 사용해야한다.

""""

```
class ViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var fragments= listOf<Fragment>()
    override fun getItem(position:Int): Fragment=when(position){
        0->FirstFragment()
        1->SecondFragment()
        else->throw IllegalStateException("Unexpected position &position")
    }

    override fun getCount():Int =2

    //override fun getItem(position: Int): Fragment =fragments[position]
    //override fun getCount():Int=fragmets.size

}
```

""" 

위 방식으로 값을 넘겨주게 되면 ViewPagerAdapter는 2개 만들어져야한다.

그리고 엑티비티에서 아래처럼 Adapter를 호출해주어서 적용한다.

"""

```
class ProfileActivity : AppCompatActivity() {
    private lateinit var btmViewpageradapter: Btm_ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)//나중에 activity_profile 로 바꿔야함.

        //BTM ViewPager 넘기기
        btmViewpageradapter=Btm_ViewPagerAdapter(supportFragmentManager)
        btmViewpageradapter.fragments= listOf(
            AccountFragment(),
            RecyclerFragment(),
            EmptyFragment()
        )

        sample_bottom_viewpager.adapter=btmViewpageradapter


        //뷰페이저를 슬라이드 했을때 그에 대응되는 하단 탭 변경
        sample_bottom_viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int){
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset:Float,
                positionOffsetPixels:Int
            ){}

            override fun onPageSelected(position:Int){
                sample_bottom_navi.menu.getItem(position).isChecked=true
            }
        })

        //하단탭을 눌렀을때 뷰페이저 화면 변경
        sample_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()

            when(it.itemId){
                R.id.menu_account->index=0 //첫 화면은 인덱스 기준 0번째
                R.id.menu_recy->index=1
                R.id.menu_msg->index=2
            }
            sample_bottom_viewpager.currentItem=index
            true //불린 반환
        }

    }
```

""""



Profile/Recycler/Empty Fragment 는 BottomNavigator를 사용해 하단탭으로 슬라이드나 터치 방식으로 이동한다. 위 코드를 보면,

addOnPageChangeListener와 setOnNavigationItemSelectedListener가 그 역할을 한다.



ProfileFragment 내의 프레그먼트는 : Tab Layout을 사용한다.

ProfileFragment 내부에서 작동하므로 ProfileFragment.kt에 

```
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
    viewPagerAdapter.fragments = listOf(
        FirstFragment(),
        SecondFragment()
    )

    sample_tab_viewpager.adapter=viewPagerAdapter

    sample_tab.setupWithViewPager(sample_tab_viewpager)
    sample_tab.apply{
        getTabAt(0)?.text="INFO"
        getTabAt(1)?.text="OTHER"
    }
}
```



를 추가한다.



:tiger: : ​심화과제_ 없었음.



4번째 세미나_ 디자인 세미나

5번째 세미나_ 클디합동

# :fire:여섯번째 세미나(11/21)

-:blue_heart: 필수과제 (완성: 12/4)

postman으로 서버 확인.

[sign-up]

![Cap 2020-11-23 01-16-28-041](https://user-images.githubusercontent.com/53042824/101010924-c96a6080-35a5-11eb-8635-c37ee751c992.png)

[sign in]

![Cap 2020-11-23 01-16-59-922](https://user-images.githubusercontent.com/53042824/101010909-c7a09d00-35a5-11eb-8309-42eaaf9b0767.png)

<필수 과제 전체 모습.gif>

<img src="https://user-images.githubusercontent.com/53042824/101011017-d0916e80-35a5-11eb-9d96-898f8f73c084.gif" width="40%">

> 코드설명

> module gradle에 추가

```
// https://github.com/square/retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
// Retrofit 라이브러리 응답으로 가짜 객체를 만들기 위함
implementation 'com.squareup.retrofit2:retrofit-mock:2.9.0'
// https://github.com/google/gson
implementation 'com.google.code.gson:gson:2.8.6'
// Retrofit에서 Gson을 사용하기 위한 라이브러리
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

> Manifest 파일에 추가

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<application
    android:usesCleartextTraffic="true"
```

> 인터페이스 설계

Headers를 추가해주고, POST인지 GET인지 명시해준다.

```
interface SoptService{

    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}
```

Call은 비동기 통신을 도와주는 Retrofit 객체이고, 서버에서 받아올 Response객체를 명시해준다. 리스트 객체인경우 List<ResponseLoginData>라 쓴다



> Request와 Response Data형태를 보여주는 객체를 생성한다.

JSON파일을 Kotlin Data Class 형태로 만들어주는 Plugin을 추가하였다.

```
data class RequestLoginData(
    val email : String,
    val password :String
)
```

```
data class ResponseLoginData(
    val data: Data,
    @SerializedName("message")
    val responseMessage: String,
    val status: Int,
    val success: Boolean
){
    data class Data(
        val email:String,
        val password: String,
        val userName: String
    )
}
```

JSON 객체의 키값과 타입을 맞춰준다. @SerializedName 어노테이션을 통해 JSON 객체의 값과 대응시킬수있다.  JSON에는 "message":"로그인성공."처럼 나와있다.

> Interface를 실제 구현체로 만든다.

```
object SoptServiceImpl {
    private const val BASE_URL = "http://15.164.83.210:3000"
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : SoptService = retrofit.create(SoptService::class.java)

}
```

싱글톤 객체이므로 Object형태이다. 

BaseURL을 적을 떄는 /를 조심해야한다.  위의 인터페이스를 설계할때 /를 썼다면 여기서는 쓰면 안된다. 겹치면 인식 못함.

retrofit 객체를  생성하는 단계로 Builder는 생성자를 호출하는 것이고, baseUrl()은 빌더객체의 URL을 호출한다. 서버의 main url 전달한다.

add~ 는 gson을 연동하는 역할을한다. (레트로핏 데이터를 다루기 쉽게), build는 반환한다. 

> 원래 Activity에서 Callback 등록하여 통신요청

```
login_btn.setOnClickListener{
    id=id_input.text.toString()
    pw=pw_input.text.toString()

    val call: Call<ResponseLoginData> = SoptServiceImpl.service.postLogin(
        RequestLoginData(email = id, password = pw)
    )
    call.enqueue(object : Callback<ResponseLoginData> {
        override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
            //통신 실패 로직
            Toast.makeText(this@MainActivity, "로그인 실패", Toast.LENGTH_LONG).show()
        }

        override fun onResponse(
            call: Call<ResponseLoginData>,
            response: Response<ResponseLoginData>
        ) {
            response.takeIf { it.isSuccessful }
                ?.body()
                ?.let { it ->
                    it.data.let { data ->
                        Toast.makeText(this@MainActivity,"${data.userName} 님 환영합니다.",Toast.LENGTH_LONG).show()
                    }

                } ?: showError(response.errorBody())
        }

        private fun showError(error: ResponseBody?) {
            val e = error ?: return
            val ob = JSONObject(e.string())
            Toast.makeText(this@MainActivity, ob.getString("message"), Toast.LENGTH_LONG)
                .show()
        }
    })
}
```

여기서는 로그인 버튼을 눌렀을 때 수행하도록 하기 위해 setonClickListener 안에 call을 넣었다.

onFailure는 네트워크 연결이 안되었을 때 발생하므로, 네트워크 꼭 확인하자...^^

onResponse는 정상수행된 경우로 data를 받아와서 userName을 Toast메세지로 알려준다.

ShowError는 네트워크는 정상 연결이지만 에러가 발생할때 문제 상황을 알려준다.



 -:tiger: : 심화과제1 ( 완성: 12/11)

<img src="https://user-images.githubusercontent.com/53042824/101898189-06aaa000-3bef-11eb-8637-27dd46047d2a.gif" width="40%">

[Reqres - A hosted REST-API ready to respond to your AJAX requests](https://reqres.in/)

reqres.in 사이트에서 더미데이터를 받아와서 진행.

전체적인 구현은 위와 같으므로 헷갈렸던 부분만 명시.( 틀린 부분을 알려준 다빈언니 땡큐:blue_heart: )

> Glide를 사용해 avatar, 즉 그림을 받아올 수 있다. 

```
Glide.with(itemView)
        .load(data.avatar)
        .placeholder(R.drawable.earth)
        .error(R.drawable.earth)
        .into(avatar)
```

placeholder는 로딩중 그림이고, error는 에러발생시 그림이다. avatar라는 xml 사진 칸에 들어간다.

itemView부분을 계속 this라고 써서 에러가 났다.



> BaseUrl 조심하기

```
object ReqresServiceImpl {
    private const val BASE_URL = "https://reqres.in/"
    //뒤에 더 내용
}
```



> ReqreAdapter에서 데이터를 받아올때, 다음객체까지 넘겨받아와야함.

```
var data= mutableListOf<ReqresData.DataX>()
```

ReqresData만 집어넣고, 그 다음 내부 코드에서 .data.dataX로 쓰려고 했더니 작동하지 않았다.

```
holder.onBind(data[position])
```

이 부분도 작동하지 않게 되기 때문에 위처럼 작성해야한다. ReqresData.DataX로
