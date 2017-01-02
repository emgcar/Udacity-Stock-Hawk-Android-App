package com.udacity.stockhawk.ui;

import android.content.ContentValues;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.PrefUtils;

import java.util.List;

import yahoofinance.histquotes.HistoricalQuote;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    GraphView graph;
    TextView stockLabel;
    TextView attributeLabel;
    ImageView attributeImage;

    static final String DETAIL_URI = "URI";
    String symbol;
    String[] history;


    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle args = getArguments();
        symbol = (String) args.get(getString(R.string.symbol_key));
        history = PrefUtils.getStockHistory(getActivity(), symbol);
        DataPoint[] data = PrefUtils.historyToDataPoints(history);

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        graph = (GraphView) rootView.findViewById(R.id.history_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);

        stockLabel = (TextView) rootView.findViewById(R.id.symbol);
        stockLabel.setText(symbol);

        attributeLabel = (TextView) rootView.findViewById(R.id.attribute_label);
        attributeLabel.setText(getString(R.string.graph_view_attribution));

        return rootView;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loaderCursor, Cursor cursor) {


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loaderCursor) {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }
}
