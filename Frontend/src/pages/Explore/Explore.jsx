import React from "react";
import "./Explore.scss";
import CarCard from "../../components/CarCard/CarCard";
import { listBrand, listTopCar } from "../../common/data/mockData";
import FilterItem from "../../components/FilterItem/FilterItem";

const Explore = () => {
    const listCarsCard = listTopCar?.map((v) => {
        return <CarCard props={v} />;
    });

    return (
        <div className="explore-container">
            <div className="back-head"></div>

            <div className="explore--head">
                <h1>Explore</h1>
            </div>
            <div className="explore--main">
                <aside>
                    <div className="aside-head">
                        <h2>Filter</h2>
                        <span>CLEAR ALL FILTERS</span>
                    </div>
                    <FilterItem />
                    <FilterItem />
                    <FilterItem />
                    <FilterItem />
                    <FilterItem />
                    <FilterItem />
                </aside>
                <main>
                    <div className="sortBar">
                        <div className="titleCount">
                            <h2>Choose your vehicle</h2>
                            <span>32 available</span>
                        </div>
                        <div className="sortSelect">
                            <span>SORT BY</span>
                            <select name="" id="">
                                <option>Most popular</option>
                                <option>Most popular</option>
                                <option>Most popular</option>
                            </select>
                        </div>
                    </div>
                    <div className="listCar">{listCarsCard}</div>
                </main>
            </div>
        </div>
    );
};

export default Explore;
