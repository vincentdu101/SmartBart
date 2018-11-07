import * as React from "react";
import "./StationSection.css";
import { IStationSectionProps, IStationSectionState, IStationInfo } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";
import { Map } from "../../Maps/Map";
import { MapService } from "../../../services/MapService/MapService";
import { CircleTooltip } from "../../Tooltips/CircleTooltip"; 
import { ICircleEvent } from "../../../types/MapTypes";
// import StationsTable from "../../StationsTable/StationsTable";
import { Card, CardText, CardBody, CardTitle, Button } from "reactstrap";

export default class StationSection extends React.Component<IStationSectionProps, IStationSectionState> {

    constructor(props: IStationSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.loadMapsInfo = this.loadMapsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.mapHoveredStation = this.mapHoveredStation.bind(this);
        this.outputBartText = this.outputBartText.bind(this);
        this.outputStationCards = this.outputStationCards.bind(this);

        this.state = {
            stations: [],
            maps: null,
            tooltipStation: undefined,
            tooltipX: 0,
            tooltipY: 0,
            tooltipActive: false,
            tooltipTextCallback: this.outputBartText
        };
    }

    public componentDidMount(): void {
        this.setState({
            stations: [],
            maps: null, 
            tooltipStation: undefined,
            tooltipX: 0, 
            tooltipY: 0,
            tooltipActive: false,
            tooltipTextCallback: this.outputBartText
        });
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
        const station = this.state.stations[parseInt(position.target.dataset.index)];
        
        this.setState({
            tooltipX: position.x,
            tooltipY: position.y,
            tooltipStation: station,
            tooltipActive: true,
            tooltipTextCallback: this.outputBartText
        });
    }

    private outputBartText(station: IStationInfo): JSX.Element {
        if (station) {
            return (
                <div className="bart-text">
                    <div>{station.name} - {station.abbr}</div>
                    <div>{station.address}</div>
                    <div>{station.city}, {station.state} {station.zipcode}</div>
                </div>
            );
        } else {
            return (<div>test</div>);
        }
    }

    private outputStationCards(): JSX.Element[] {
        return this.state.stations.map((station, index) => {
            return (
                <div className="col-xs-12 col-sm-3 station-card" key={"station-card-wrap" + index}>
                    <Card key={"station-card-" + index}>
                        <CardBody key={"station-body-" + index}>
                            <CardTitle key={"station-title-" + index}>{station.abbr}</CardTitle>
                            <CardText key={"station-text-" + index}>
                                <div className="bart-text">
                                    <div>{station.address}</div>
                                    <div>{station.city}, {station.state} {station.zipcode}</div>
                                </div>
                            </CardText>
                            <Button key={"station-btn-" + index}>Button</Button>
                        </CardBody>
                    </Card>
                </div>
            );
        });
    }

    public render(): JSX.Element {

        return (
            <section className="stations-section container no-gutters">
                <div className="row">

                    <div className="col-xs-12">
                        <div className="card">
                            <Map    maps={this.state.maps} 
                                    stations={this.state.stations}
                                    hoverCallback={this.mapHoveredStation} />

                            <CircleTooltip  x={this.state.tooltipX}
                                            y={this.state.tooltipY}
                                            station={this.state.tooltipStation}
                                            tooltipActive={this.state.tooltipActive}
                                            text={this.state.tooltipTextCallback} />
                        </div>
                    </div>
                </div>
                <div className="row no-gutters">
                    {this.outputStationCards()}
                </div>
            </section>
        );

    }

}
