package com.example.movieapp.ui.view




import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapter.FilmAdapter
import com.example.movieapp.data.model.Search
import com.example.movieapp.databinding.SearchFragmentBinding
import com.example.movieapp.ui.viewmodel.DetailFilmViewModel
import com.example.movieapp.ui.viewmodel.SearchViewModel
import com.example.movieapp.ui.viewmodel.UserViewModel
import com.example.movieapp.util.ApiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    //ViewModel
    private val viewModel by viewModels<SearchViewModel>()
    private val detailviewModel by viewModels<DetailFilmViewModel>()

    //Binding
    private lateinit var binding : SearchFragmentBinding
    private lateinit var toolbar : Toolbar



    //ui var
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var myRecyclerView: RecyclerView
    var searchView:SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SearchFragmentBinding.bind(view)
        myRecyclerView= binding.isMyRecyclerView


        var texto= arguments?.getString("busqueda")
        if (!texto.isNullOrEmpty()) {
            Log.d("Mari Carmen:", "ss"+texto)

            viewModel.getSearchResult(texto)
            chargueFilms()
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)

        return view
    }




    //----RecyclerView
    private fun nRecyclerView(films: List<Search>){
        var listOfFilms :List<Search>
        listOfFilms=films
        filmAdapter = FilmAdapter(listOfFilms)
        myRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(context)
        }

        myRecyclerView.adapter= filmAdapter



        filmAdapter.setOnItemClickListener(object: FilmAdapter.OnClickListener{
            override fun onItemClick(position: Int) {
                detailviewModel.getFilmResult(films[position].title)
                Toast.makeText(context,"Título :"+films[position].title, Toast.LENGTH_LONG).show()
                sendTitle(films[position].title)
                setupBottomDialog()

            }
        } )

    }

    // --- Chargue Films of Viewmodel
    private fun chargueFilms(){
        var mListOfFilms: List<Search>

        lifecycleScope.launchWhenCreated {
            viewModel._stateFlow.collect { it-> when(it)
            { is ApiState.Succes-> {

                    mListOfFilms =  it.data.Search as List<Search>
                    nRecyclerView(mListOfFilms) }
            }

            }
        }

    }

     fun setupBottomDialog(){
         activity?.supportFragmentManager.let {

                                        BottomDetailSheet.newInstance(Bundle()).apply {
                                            show(it!!,tag)
                                        }
                                    }
     }



    private fun sendTitle(sendtitle: String){
        val result = sendtitle
            setFragmentResult("titleKey", bundleOf("bundleKey" to result))
    }





}



