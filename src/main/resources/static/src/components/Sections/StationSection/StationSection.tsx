import * as React from "react";
import "./StationSection.css";
import { IStationSectionProps, IStationSectionState } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";
import ListGroupInfo from "../../ListGroup/ListGroupInfo";
import { Map } from "../../Maps/Map";
import { MapService } from "../../../services/MapService/MapService";
import { CircleTooltip } from "../../Tooltips/CircleTooltip"; 
import { ICircleEvent } from "../../../types/MapTypes";

export default class StationSection extends React.Component<IStationSectionProps, IStationSectionState> {

    constructor(props: IStationSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.loadMapsInfo = this.loadMapsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.mapHoveredStation = this.mapHoveredStation.bind(this);

        this.state = {
            stations: [],
            maps: null,
            tooltipX: 0,
            tooltipY: 0
        };
    }

    public componentDidMount(): void {
        this.setState({stations: [], maps: null, tooltipX: 0, tooltipY: 0});
        this.loadStationsInfo();
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: data});
        });
    }

    private loadMapsInfo(): void {
        MapService.getMapData().then((mapData: any) => {
            this.setState({maps: mapData.data});
        });
    }

    private originSelection(station: string): void {
        console.log(station);
    }

    private mapHoveredStation(position: ICircleEvent): void {
        console.log(position);
        this.setState({
            tooltipX: position.x,
            tooltipY: position.y
        })
    }

    public render(): JSX.Element {

        return (
            <section className="stations-section container">
                <div className="row">
                    <div className="col-md-4">
                        <ListGroupInfo  label="stations" 
                                        input={this.state.stations}
                                        selectionCallback={this.originSelection} />
                    </div>

                    <div className="col-md-8">
                        <div className="panel">
                            <Map    maps={this.state.maps} 
                                    stations={this.state.stations}
                                    hoverCallback={this.mapHoveredStation} />

                            <CircleTooltip  text={"test"} 
                                            x={this.state.tooltipX}
                                            y={this.state.tooltipY}
                                            tooltipActive={true} />
                        </div>
                    </div>
                </div>
            </section>
        );

    }

}
