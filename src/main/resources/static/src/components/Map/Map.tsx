import * as React from "react";
import {IMapProps, IMapState} from "../../types/MapTypes";
import { geoMercator, geoPath } from "d3-geo";
import { feature } from "topojson-client";

export default class Map extends React.Component<IMapProps, IMapState> {

    constructor(props: any) {
        super(props);

        this.loadMapData = this.loadMapData.bind(this);
        this.createUSMap = this.createUSMap.bind(this);
    }

    public componentDidMount(): void {
        this.setState({usData: {}});
        this.loadMapData();
    }   

    private loadMapData(): void {
        fetch("https://d3js.org/us-10m.v1.json").then(response => {
            return response.json();
        }).then((data) => {
            this.setState({usData: feature(data, data.objects.states).features});
        });
    }
    
    private createUSMap() {

    }

    public render(): JSX.Element {
        return (

        );
    }


}