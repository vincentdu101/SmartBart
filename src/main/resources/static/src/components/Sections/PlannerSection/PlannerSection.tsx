import * as React from "react";
import "./PlannerSection.scss";
import { IStationInfo } from "../../../types/StationTypes";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import DropdownInfo from "../../Dropdown/DropdownInfo";
import { StationService } from "../../../services/StationService/StationService";
import { Map } from "../../Maps/Map";
import { ICircleEvent } from "../../../types/MapTypes";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.destinationSelection = this.destinationSelection.bind(this);
        this.addStationToMapList = this.addStationToMapList.bind(this);
        this.mapHoveredStation = this.mapHoveredStation.bind(this);
        this.outputMapHoverInfo = this.outputMapHoverInfo.bind(this);

        this.state = {
            maps: null,
            stations: [],
            mapStations: [],
            mapSelectedStations: [],
            focusedStations: [],
            origin: "",
            destination: "",
            tooltipStation: undefined,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        };
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: data, mapStations: data});
        });
    }

    private mapHoveredStation(position: ICircleEvent): void {
        const station = this.state.mapStations[parseInt(position.target.dataset.index)];
        
        this.setState({
            tooltipStation: station,
            tooltipActive: true,
            tooltipTextCallback: StationService.outputBartText
        });
    }

    private addStationToMapList(station: string): IStationInfo[] {
        let selectedStation = StationService.filterForStation(station, this.state.mapStations);
        let mapSelectedStations = this.state.mapSelectedStations;
        mapSelectedStations.push(selectedStation);
        return mapSelectedStations;
    }

    private originSelection(origin: string): void {
        this.setState({origin: origin, mapSelectedStations: this.addStationToMapList(origin)});
    }

    private destinationSelection(destination: string): void {
        this.setState({destination: destination, mapSelectedStations: this.addStationToMapList(destination)});
    }

    private outputMapHoverInfo(position: ICircleEvent): JSX.Element {
        if (!!position) {
            const station = this.state.mapStations[parseInt(position.target.dataset.index)];
            return StationService.outputBartText(station);
        } else {
            return (<input type="hidden" />);
        }
    }

    public componentDidMount(): void {
        this.setState({
            tooltipStation: undefined,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        });

        this.loadStationsInfo();
    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <div className="row planner-menu-row">
                    <div className="col-xs-6 first-col">
                        <DropdownInfo   input={this.state.stations}
                                        label="Origin" 
                                        selectionCallback={this.originSelection} />
                    </div>
                    <div className="col-xs-6">
                        <DropdownInfo   input={this.state.stations} 
                                        label="Destination"
                                        selectionCallback={this.destinationSelection} />
                    </div>
                </div>
                <PlannerTable   origin={this.state.origin} 
                                destination={this.state.destination} />

                <Map    maps={this.state.maps} 
                        stations={this.state.mapSelectedStations}
                        console={true}
                        outputMapHoverInfo={this.outputMapHoverInfo} />                                                     
            </section>
        );
    }

}