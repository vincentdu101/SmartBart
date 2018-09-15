import * as React from "react";
import {IMapLeafletProps, IMapLeafletState} from "../../types/MapLeafletTypes";
import { Map, Marker, Popup, TileLayer } from 'react-leaflet';

export default class MapLeaflet extends React.Component<IMapLeafletProps, IMapLeafletState> {

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

    }
    
    private createUSMap() {

    }

    public render(): JSX.Element {
        const position = {lat: 51.505, lng: -0.09};

        return (
            <Map center={position} zoom={13}>
                <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                attribution="&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
                />
                <Marker position={position}>
                <Popup>A pretty CSS3 popup.<br />Easily customizable.</Popup>
                </Marker>
            </Map>
        );
    }


}