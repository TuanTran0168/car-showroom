import React from "react";
import "./SearchHome.scss";

const SearchHome = () => {
    return (
        <div className="flex items-center justify-between bg-white p-2 rounded-full shadow-md max-w-4xl w-full mx-auto gap-2 my-6">
            {/* Makes */}
            <select className="bg-transparent px-4 py-2 text-sm text-gray-700 rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500 appearance-none border-r border-gray-300">
                <option disabled selected>
                    Any Makes
                </option>
                <option>Audi</option>
                <option>BMW</option>
                <option>Mercedes</option>
            </select>

            {/* Models */}
            <select className="bg-transparent px-4 py-2 text-sm text-gray-700 rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500 appearance-none border-r border-gray-300">
                <option disabled selected>
                    Any Models
                </option>
                <option>Q7</option>
                <option>X5</option>
                <option>C-Class</option>
            </select>

            {/* Prices */}
            <div className="flex items-center gap-2 px-4 py-2 border-r border-gray-300">
                <span className="text-sm text-gray-500">Prices:</span>
                <select className="bg-transparent text-sm text-gray-700 focus:outline-none">
                    <option>All Prices</option>
                    <option>$0 - $50,000</option>
                    <option>$50,000 - $100,000</option>
                </select>
            </div>

            {/* Search Button */}
            <button className="search-button text-white px-6 py-2 text-sm flex items-center gap-2">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-4 w-4"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                >
                    <path
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth={2}
                        d="M21 21l-4.35-4.35m0 0A7.5 7.5 0 1110.5 3a7.5 7.5 0 016.15 13.65z"
                    />
                </svg>
                Search Cars
            </button>
        </div>
    );
};

export default SearchHome;
